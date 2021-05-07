package com.psss.homequrantine.RecoveryCovid;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.psss.homequrantine.R;

public class Rc_homepage extends AppCompatActivity {

    LinearLayout rc_exercise,rc_memorygame,rc_nutritious;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_homepage);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Recovered from COVID");
        setSupportActionBar(toolbar);

        rc_exercise = findViewById(R.id.rc_exercise);
        rc_memorygame = findViewById(R.id.rc_memorygame);

        rc_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Rc_homepage.this , Rc_exercise_start.class);
                startActivity(intent);
            }
        });

        rc_memorygame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Rc_homepage.this ,Rc_memorygame_start.class);
                startActivity(intent);
            }
        });

        /*rc_nutritious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (Rc_homepage.this , Rc_nutritious_start.class);
                startActivity(intent);
            }
        });*/
    }
}