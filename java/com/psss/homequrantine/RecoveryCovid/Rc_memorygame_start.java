package com.psss.homequrantine.RecoveryCovid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.psss.homequrantine.Adapter.Rc_memorygame_fragadapter;
import com.psss.homequrantine.R;

public class Rc_memorygame_start extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_memorygame_start);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Memory Games");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        Rc_memorygame_fragadapter adapter = new Rc_memorygame_fragadapter(getSupportFragmentManager());
        // Set the adapter onto
        // the view pager
        viewPager.setAdapter(adapter);

    }
}