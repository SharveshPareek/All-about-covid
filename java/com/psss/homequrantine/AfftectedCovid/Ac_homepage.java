package com.psss.homequrantine.AfftectedCovid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.psss.homequrantine.Home;
import com.psss.homequrantine.Model.SignupDB;
import com.psss.homequrantine.Model.UploadDB;
import com.psss.homequrantine.QurantineDays.QHome;
import com.psss.homequrantine.R;

import java.util.ArrayList;
import java.util.List;

public class Ac_homepage extends AppCompatActivity {

    LinearLayout ac_self_diagnosis,ac_self_quarantine,ac_habits;

    CardView ac_upload;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference  QPref,signupref,checkref,QurantineDaysref;

    List<String> days = new ArrayList<>();

    String name,pincode;

    Toolbar toolbar;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_homepage);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Affected by COVID");
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        checkref = database.getReference();
        QPref = database.getReference("Qurantine Peoples");
        signupref = database.getReference("Sign Up");
        QurantineDaysref = database.getReference("14 Days Qurantine");

        progressDialog = new ProgressDialog(this);

        days.add("Day 1");
        days.add("Day 2");
        days.add("Day 3");
        days.add("Day 4");
        days.add("Day 5");
        days.add("Day 6");
        days.add("Day 7");
        days.add("Day 8");
        days.add("Day 9");
        days.add("Day 10");
        days.add("Day 11");
        days.add("Day 12");
        days.add("Day 13");
        days.add("Day 14");
        days.add("Day 15");

        ac_self_diagnosis = findViewById(R.id.ac_self_diagnosis);
        ac_self_quarantine = findViewById(R.id.ac_self_quarantine);
        ac_habits = findViewById(R.id.ac_habits);
        ac_upload = findViewById(R.id.ac_upload);

        ac_self_diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Ac_homepage.this , Ac_self_diagnosis_start.class);
                startActivity(intent);
            }
        });

        ac_self_quarantine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please wait a moment");
                progressDialog.setCancelable(false);
                progressDialog.show();

                checkref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/"+mAuth.getUid())){
                            progressDialog.dismiss();
                            Intent intent = new Intent(Ac_homepage.this, QHome.class);
                            startActivity(intent);
                        }else {
                            QurantineDaysref.child(mAuth.getUid()).setValue(days).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(Ac_homepage.this, QHome.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Ac_homepage.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Ac_homepage.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        ac_habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (Ac_homepage.this , Ac_habits_start.class);
                startActivity(intent);
            }
        });

        ac_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Ac_homepage.this,R.style.AlertDialogStyle);
                builder.setCancelable(false);
                builder.setMessage("If you are affected by COVID-19.\nUpload your details and Aware your Neighbours and Relativies.");
                builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog.setTitle("Uploading!!!");
                        progressDialog.setMessage("Please wait a moment to upload");
                        progressDialog.setCancelable(false);
                        progressDialog.show();


                        signupref.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                SignupDB signupDB = snapshot.getValue(SignupDB.class);
                                assert signupDB != null;
                                name = signupDB.getUsername();
                                pincode = signupDB.getPincode();

                                checkref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if (snapshot.hasChild("Qurantine Peoples/"+ pincode + "/" + mAuth.getUid())){

                                            progressDialog.dismiss();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Ac_homepage.this,R.style.AlertDialogStyle);
                                            builder.setMessage("You have allready uploaded!!!\nAre you want to delete your name from the list?")
                                                    .setCancelable(false);

                                            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    FirebaseDatabase.getInstance().getReference("Qurantine Peoples/" + pincode + "/" + mAuth.getUid()).removeValue();
                                                    dialog.dismiss();
                                                    Toast.makeText(Ac_homepage.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                                                }
                                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });

                                            AlertDialog alert = builder.create();
                                            alert.show();

                                        }else{

                                            uploadMe(name, pincode);

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    private void uploadMe(final String name, String pincode) {

        UploadDB uploadDB = new UploadDB(name,pincode);
        QPref.child(pincode).child(mAuth.getUid()).setValue(uploadDB).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                Toast.makeText(Ac_homepage.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Ac_homepage.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Ac_homepage.this, Home.class);
        startActivity(intent);
        finish();
    }
}

/*upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });*/