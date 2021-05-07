package com.psss.homequrantine.BeforeCovid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.psss.homequrantine.Adapter.Bc_precaution_frag_pageadapter;
import com.psss.homequrantine.R;

public class Bc_precaution_start extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bc_precaution_start);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Precaution");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        Bc_precaution_frag_pageadapter adapter = new Bc_precaution_frag_pageadapter(getSupportFragmentManager());
        // Set the adapter onto
        // the view pager
        viewPager.setAdapter(adapter);
    }
}
