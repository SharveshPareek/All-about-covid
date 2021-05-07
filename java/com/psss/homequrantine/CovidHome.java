package com.psss.homequrantine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class CovidHome extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_home);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("COVID-19");
        setSupportActionBar(toolbar);

    }
}
