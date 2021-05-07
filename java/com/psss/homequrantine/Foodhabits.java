package com.psss.homequrantine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class Foodhabits extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodhabits);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Food Habits");
        setSupportActionBar(toolbar);

    }
}
