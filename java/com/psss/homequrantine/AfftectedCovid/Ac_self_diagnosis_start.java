package com.psss.homequrantine.AfftectedCovid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.psss.homequrantine.Adapter.Ac_self_diagnosis_fragadapter;
import com.psss.homequrantine.R;

public class Ac_self_diagnosis_start extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_self_diaganosis);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Self Diaganosis");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        Ac_self_diagnosis_fragadapter adapter = new Ac_self_diagnosis_fragadapter(getSupportFragmentManager());
        // Set the adapter onto
        // the view pager
        viewPager.setAdapter(adapter);
    }
}