package com.psss.homequrantine.BeforeCovid;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.psss.homequrantine.R;

public class Bc_homepage extends AppCompatActivity {

    LinearLayout bc_precautions,bc_vaccination;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_covid_homepage);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Before COVID");
        setSupportActionBar(toolbar);

        bc_precautions = findViewById(R.id.bc_precautions);
        bc_vaccination = findViewById(R.id.bc_vaccination);

        bc_precautions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Bc_homepage.this , Bc_precaution_start.class);
                startActivity(intent);
            }
        });

        bc_vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (Bc_homepage.this , Bc_vaccination_start.class);
                startActivity(intent);
            }
        });
    }

}