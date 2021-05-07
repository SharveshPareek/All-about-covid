package com.psss.homequrantine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView signup,forgotPass;

    String emailInput, passInput;

    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        forgotPass = findViewById(R.id.forgotpass);

        progressDialog = new ProgressDialog(Login.this);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailInput = email.getText().toString();
                passInput = password.getText().toString();

                if (emailInput.isEmpty() && passInput.isEmpty()) {
                    Toast.makeText(Login.this, "", Toast.LENGTH_SHORT).show();
                } else if (emailInput.isEmpty()) {
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (passInput.isEmpty()) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (!Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", emailInput)) {
                    Toast.makeText(Login.this, "Enter Valid email", Toast.LENGTH_SHORT).show();
                } else if (passInput.length() < 6) {
                    Toast.makeText(Login.this, "Password must be atleast 8 character", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.setTitle("Loging In!!");
                    progressDialog.setMessage("Please wait while we are logging you in...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(emailInput, passInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, task.getException().getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Login.this, R.style.PauseDialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forgot_dialog);
                dialog.setCanceledOnTouchOutside(false);

                final EditText email = dialog.findViewById(R.id.forgot_email);
                Button cancel = dialog.findViewById(R.id.cancel);
                Button send = dialog.findViewById(R.id.send);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String emailInput = email.getText().toString();
                        if (emailInput.isEmpty()) {
                            Toast.makeText(Login.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                        } else if (!Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", emailInput)) {
                            Toast.makeText(Login.this, "Enter valid Email id", Toast.LENGTH_SHORT).show();
                        } else {
                            mAuth.sendPasswordResetEmail(emailInput)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                dialog.dismiss();
                                                Toast.makeText(Login.this, "Email sent", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
