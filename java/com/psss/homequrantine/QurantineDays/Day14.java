package com.psss.homequrantine.QurantineDays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.psss.homequrantine.AfftectedCovid.Ac_homepage;
import com.psss.homequrantine.Model.QDaysDB;
import com.psss.homequrantine.Model.SignupDB;
import com.psss.homequrantine.R;

public class Day14 extends AppCompatActivity {

    LinearLayout selfCheckLL;

    TextView exercise_show,exercise_hide;
    HorizontalScrollView ex_scroll;

    TextView tv_completed,tv_report;
    RadioGroup fever, cough, taste;
    RadioButton flow,fnormal, fhigh, clow, cnormal, chigh, ftlow, ftnormal, fthigh;
    Button complete;

    String feverIn, coughIn, tasteIn, pincode;
    String completedStatus,reportDB,reportIn;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref,removeRef,QPref,signupref,QurantineDaysref;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day14);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        completedStatus = bundle.getString("Completed");
        reportDB = bundle.getString("Report");
        QurantineDaysref = database.getReference("14 Days Qurantine");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Day 14");
        setSupportActionBar(toolbar);

        fever = findViewById(R.id.fever);
        flow = findViewById(R.id.flow);
        fnormal = findViewById(R.id.fnormal);
        fhigh = findViewById(R.id.fhigh);
        cough = findViewById(R.id.cough);
        clow = findViewById(R.id.clow);
        cnormal = findViewById(R.id.cnormal);
        chigh = findViewById(R.id.chigh);
        taste = findViewById(R.id.taste);
        ftlow = findViewById(R.id.ftlow);
        ftnormal = findViewById(R.id.ftnormal);
        fthigh = findViewById(R.id.fthigh);
        complete = findViewById(R.id.complete);
        complete.setVisibility(View.GONE);
        tv_report = findViewById(R.id.tv_report);
        tv_report.setVisibility(View.GONE);
        tv_completed = findViewById(R.id.tv_completed);
        tv_completed.setVisibility(View.GONE);
        selfCheckLL = findViewById(R.id.selfcheckLL);
        selfCheckLL.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Reports");
        removeRef = database.getReference("14 Days Qurantine");
        QPref = database.getReference("Qurantine Peoples");
        signupref = database.getReference("Sign Up");

        exercise_show = findViewById(R.id.show_exercise_showcard);
        exercise_hide = findViewById(R.id.hide_exercise_showcard);
        exercise_hide.setVisibility(View.GONE);
        ex_scroll = findViewById(R.id.ex_scroll);
        ex_scroll.setVisibility(View.GONE);

        exercise_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exercise_show.setVisibility(View.GONE);
                exercise_hide.setVisibility(View.VISIBLE);
                ex_scroll.setVisibility(View.VISIBLE);

            }
        });

        exercise_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exercise_show.setVisibility(View.VISIBLE);
                exercise_hide.setVisibility(View.GONE);
                ex_scroll.setVisibility(View.GONE);

            }
        });

        if (completedStatus.matches("YES")){
            tv_completed.setVisibility(View.VISIBLE);
            tv_report.setVisibility(View.VISIBLE);
            tv_report.setText(reportDB);
            selfCheckLL.setVisibility(View.GONE);
            complete.setVisibility(View.GONE);
        }else if (completedStatus.matches("NO")){
            tv_completed.setVisibility(View.GONE);
            tv_report.setVisibility(View.GONE);
            selfCheckLL.setVisibility(View.VISIBLE);
            complete.setVisibility(View.VISIBLE);
        }

        fever.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.flow:
                        feverIn = flow.getText().toString();
                        break;
                    case R.id.fnormal:
                        feverIn = fnormal.getText().toString();
                        break;
                    case R.id.fhigh:
                        feverIn = fhigh.getText().toString();
                        break;
                }
            }
        });

        cough.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.clow:
                        coughIn = clow.getText().toString();
                        break;
                    case R.id.cnormal:
                        coughIn= cnormal.getText().toString();
                        break;
                    case R.id.chigh:
                        coughIn = chigh.getText().toString();
                        break;
                }
            }
        });

        taste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.ftlow:
                        tasteIn = ftlow.getText().toString();
                        break;
                    case R.id.ftnormal:
                        tasteIn = ftnormal.getText().toString();
                        break;
                    case R.id.fthigh:
                        tasteIn = fthigh.getText().toString();
                        break;
                }
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fever.getCheckedRadioButtonId() == -1 && cough.getCheckedRadioButtonId() == -1 && taste.getCheckedRadioButtonId() == -1 ) {
                    Toast.makeText(getApplicationContext(), "Fill the self check symptoms", Toast.LENGTH_SHORT).show();
                } else if (fever.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Select your fever level", Toast.LENGTH_SHORT).show();
                } else if (cough.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Select your cough level", Toast.LENGTH_SHORT).show();
                } else if (taste.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Select your taste level", Toast.LENGTH_SHORT).show();
                } else {

                    progressDialog = new ProgressDialog(Day14.this);
                    progressDialog.setTitle("Saving your Day 14 activities");
                    progressDialog.setMessage("Please wait while we are saving");

                    if ( ( feverIn.matches("Low") && coughIn.matches("Low") && tasteIn.matches("Low") ) ||
                            (feverIn.matches("Low") && coughIn.matches("Low") && tasteIn.matches("Normal")) ||
                            (feverIn.matches("Low") && coughIn.matches("Normal") && tasteIn.matches("Low")) ||
                            (feverIn.matches("Normal") && coughIn.matches("Low") && tasteIn.matches("Low")) ) {

                        reportIn = " You are quarantining safely.\n Stay at home.\n Follow the advisory.";

                        progressDialog.show();

                        QDaysDB qDaysDB = new QDaysDB(feverIn, coughIn, tasteIn,reportIn);
                        ref.child(mAuth.getUid()).child("Day 14").setValue(qDaysDB).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                signupref.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        SignupDB signupDB = snapshot.getValue(SignupDB.class);
                                        assert signupDB != null;
                                        pincode = signupDB.getPincode();

                                        progressDialog.dismiss();
                                        removeRef.child(mAuth.getUid()).child("13").removeValue();
                                        QPref.child(pincode).child(mAuth.getUid()).removeValue();
                                        QurantineDaysref.child(mAuth.getUid()).removeValue();
                                        Toast.makeText(getApplicationContext(), reportIn, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Ac_homepage.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else if ( (feverIn.matches("Normal") && coughIn.matches("Normal") && tasteIn.matches("Normal") ) ||
                            (feverIn.matches("High") && coughIn.matches("Normal") && tasteIn.matches("Normal")) ||
                            (feverIn.matches("Normal") && coughIn.matches("Normal") && tasteIn.matches("High")) ||
                            (feverIn.matches("Normal") && coughIn.matches("High") && tasteIn.matches("Normal")) ){

                        reportIn = " Stay with more concious on advisory\n You are recovering normally";

                        progressDialog.show();

                        QDaysDB qDaysDB = new QDaysDB(feverIn, coughIn, tasteIn,reportIn);
                        ref.child(mAuth.getUid()).child("Day 14").setValue(qDaysDB).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                signupref.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        SignupDB signupDB = snapshot.getValue(SignupDB.class);
                                        assert signupDB != null;
                                        pincode = signupDB.getPincode();

                                        progressDialog.dismiss();
                                        removeRef.child(mAuth.getUid()).child("13").removeValue();
                                        QPref.child(pincode).child(mAuth.getUid()).removeValue();
                                        Toast.makeText(getApplicationContext(), reportIn, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Ac_homepage.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else if ( (feverIn.matches("High") && coughIn.matches("High") && tasteIn.matches("High") ) ||
                            (feverIn.matches("High") && coughIn.matches("High") && tasteIn.matches("Normal")) ||
                            (feverIn.matches("Normal") && coughIn.matches("High") && tasteIn.matches("High")) ||
                            (feverIn.matches("High") && coughIn.matches("Normal") && tasteIn.matches("High")) ){

                        reportIn = " You may have exposed to COVID-19.\n Kindly make visit a Doctor and make yourself and your surrounding safe.";

                        progressDialog.show();

                        QDaysDB qDaysDB = new QDaysDB(feverIn, coughIn, tasteIn,reportIn);
                        ref.child(mAuth.getUid()).child("Day 14").setValue(qDaysDB).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                signupref.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        SignupDB signupDB = snapshot.getValue(SignupDB.class);
                                        assert signupDB != null;
                                        pincode = signupDB.getPincode();

                                        progressDialog.dismiss();
                                        removeRef.child(mAuth.getUid()).child("13").removeValue();
                                        QPref.child(pincode).child(mAuth.getUid()).removeValue();
                                        Toast.makeText(getApplicationContext(), reportIn, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Ac_homepage.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {

                        reportIn = " Stay with more concious on advisory\n You are recovering normally";

                        progressDialog.show();

                        QDaysDB qDaysDB = new QDaysDB(feverIn, coughIn, tasteIn,reportIn);
                        ref.child(mAuth.getUid()).child("Day 14").setValue(qDaysDB).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                signupref.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        SignupDB signupDB = snapshot.getValue(SignupDB.class);
                                        assert signupDB != null;
                                        pincode = signupDB.getPincode();

                                        progressDialog.dismiss();
                                        removeRef.child(mAuth.getUid()).child("13").removeValue();
                                        QPref.child(pincode).child(mAuth.getUid()).removeValue();
                                        Toast.makeText(getApplicationContext(), reportIn, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Ac_homepage.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }
            }
        });

    }
}
