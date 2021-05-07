package com.psss.homequrantine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.psss.homequrantine.Model.SignupDB;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {

    EditText username, email, age, street, area, city, district, pincode, mob, password, cpassword;
    Button Signup;

    String usernameIn, emailIn, ageIn, streetIn, areaIn, cityIn, districtIn, pincodeIn, mobIn, passwordIn, cpasswordIn;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        street = findViewById(R.id.street);
        area = findViewById(R.id.area);
        city = findViewById(R.id.city);
        district = findViewById(R.id.district);
        pincode = findViewById(R.id.pincode);
        mob = findViewById(R.id.mob);
        password = findViewById(R.id.pass);
        cpassword = findViewById(R.id.cpass);
        Signup = findViewById(R.id.signup);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Sign Up");

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameIn = username.getText().toString();
                emailIn = email.getText().toString();
                ageIn = age.getText().toString();
                streetIn = street.getText().toString();
                areaIn = area.getText().toString();
                cityIn = city.getText().toString();
                districtIn = district.getText().toString();
                pincodeIn = pincode.getText().toString();
                mobIn = mob.getText().toString();
                passwordIn = password.getText().toString();
                cpasswordIn = cpassword.getText().toString();

                if (usernameIn.isEmpty() && emailIn.isEmpty() && ageIn.isEmpty() && streetIn.isEmpty() &&
                        areaIn.isEmpty() && cityIn.isEmpty() && districtIn.isEmpty() && pincodeIn.isEmpty() && mobIn.isEmpty()
                        && passwordIn.isEmpty() && cpasswordIn.isEmpty()) {
                    Toast.makeText(Signup.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (usernameIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter Username", Toast.LENGTH_SHORT).show();
                } else if (emailIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter emailid", Toast.LENGTH_SHORT).show();
                } else if (ageIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter age", Toast.LENGTH_SHORT).show();
                } else if (streetIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter Flatno, Street name", Toast.LENGTH_SHORT).show();
                } else if (areaIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter Area", Toast.LENGTH_SHORT).show();
                } else if (cityIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter City", Toast.LENGTH_SHORT).show();
                } else if (districtIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter District", Toast.LENGTH_SHORT).show();
                } else if (pincodeIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter Pincode", Toast.LENGTH_SHORT).show();
                } else if (pincodeIn.length() < 6) {
                    Toast.makeText(Signup.this, "Enter Valid Pincode", Toast.LENGTH_SHORT).show();
                } else if (mobIn.isEmpty()) {
                    Toast.makeText(Signup.this, "Enter Mobile num", Toast.LENGTH_SHORT).show();
                } else if (mobIn.length() < 10) {
                    Toast.makeText(Signup.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", emailIn)) {
                    Toast.makeText(Signup.this, "Enter Valid email", Toast.LENGTH_SHORT).show();
                } else if (passwordIn.length() < 6) {
                    Toast.makeText(Signup.this, "Password must be atleast 8 character", Toast.LENGTH_SHORT).show();
                } else if (cpasswordIn != null && !cpasswordIn.equals(passwordIn)) {
                    Toast.makeText(Signup.this, "Confirm Password is not matching", Toast.LENGTH_SHORT).show();
                } else {

                    progressDialog = new ProgressDialog(Signup.this);
                    progressDialog.setTitle("Sigining In !!");
                    progressDialog.setMessage("Please wait while we are signing you in");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(emailIn, passwordIn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SignupDB signupDB = new SignupDB(usernameIn, emailIn, ageIn, streetIn, areaIn, cityIn, districtIn, pincodeIn, mobIn, passwordIn);
                                ref.child(mAuth.getUid()).setValue(signupDB).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Signup.this, Home.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(Signup.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }

}
