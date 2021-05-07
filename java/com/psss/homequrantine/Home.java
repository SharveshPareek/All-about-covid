package com.psss.homequrantine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.psss.homequrantine.AfftectedCovid.Ac_homepage;
import com.psss.homequrantine.BeforeCovid.Bc_homepage;
import com.psss.homequrantine.OtherMedicines.OMHome;
import com.psss.homequrantine.RecoveryCovid.Rc_homepage;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    LinearLayout hp_before_covid,hp_affected_covid,hp_recovering_covid
            ,hp_check_covid,hp_food,hp_about_covid,hp_logout,hp_othermedicine;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference checkref;

    Toolbar toolbar;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home Qurantine");
        setSupportActionBar(toolbar);

        hp_about_covid = findViewById(R.id.hp_about_covid);
        hp_before_covid= findViewById(R.id.hp_before_covid);
        hp_affected_covid = findViewById(R.id.hp_affected_covid);
        hp_recovering_covid = findViewById(R.id.hp_recovering_covid);
        hp_check_covid = findViewById(R.id.hp_check_covid);
        hp_food = findViewById(R.id.hp_food);
        hp_othermedicine = findViewById(R.id.hp_othermedicine);
        hp_logout = findViewById(R.id.hp_logout);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        checkref = database.getReference();

        progressDialog = new ProgressDialog(this);

        hp_about_covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , CovidHome.class);
                startActivity(intent);
            }
        });

        hp_before_covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , Bc_homepage.class);
                startActivity(intent);
            }
        });

        hp_affected_covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , Ac_homepage.class);
                startActivity(intent);
            }
        });

        hp_recovering_covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , Rc_homepage.class);
                startActivity(intent);
            }
        });

        hp_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , Foodhabits.class);
                startActivity(intent);
            }
        });

        hp_check_covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Home.this, R.style.PauseDialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.pincode_dialog);
                dialog.setCanceledOnTouchOutside(false);

                final EditText pincode = dialog.findViewById(R.id.pincode);
                final TextView noppl = dialog.findViewById(R.id.nopplview);
                noppl.setVisibility(View.GONE);
                Button cancel = dialog.findViewById(R.id.cancel);
                Button send = dialog.findViewById(R.id.send);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String pinInput = pincode.getText().toString();
                        if (pinInput.isEmpty()) {
                            Toast.makeText(Home.this, "Enter Pincode", Toast.LENGTH_SHORT).show();
                        } else if (pinInput.length()<6) {
                            Toast.makeText(Home.this, "Enter valid Pincode", Toast.LENGTH_SHORT).show();
                        } else {

                            progressDialog.setMessage("Please wait a monent!!!");
                            progressDialog.show();

                            checkref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if (snapshot.hasChild("Qurantine Peoples/" + pinInput)){
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(Home.this,HQPeopleList.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Pincode",pinInput);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }else {
                                        progressDialog.dismiss();
                                        noppl.setVisibility(View.VISIBLE);
                                        noppl.setText("No one has Home Qurantined in your area");
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Home.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();

            }
        });

        hp_othermedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, OMHome.class);
                startActivity(intent);

            }
        });



        hp_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent intent = new Intent(Home.this,Login.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
