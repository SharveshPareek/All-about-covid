package com.psss.homequrantine.AfftectedCovid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.psss.homequrantine.Adapter.Ac_habits_fragadapter;
import com.psss.homequrantine.R;

public class Ac_habits_start extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_habits_start);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Habits to Follow");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        Ac_habits_fragadapter adapter = new Ac_habits_fragadapter(getSupportFragmentManager());
        // Set the adapter onto
        // the view pager
        viewPager.setAdapter(adapter);
    }
}