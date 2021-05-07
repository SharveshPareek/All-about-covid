package com.psss.homequrantine.OtherMedicines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.psss.homequrantine.Adapter.OtherMed_Adapter;
import com.psss.homequrantine.Adapter.Rc_memorygame_fragadapter;
import com.psss.homequrantine.R;

public class OMHome extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omhome);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Other Medicines");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        OtherMed_Adapter adapter = new OtherMed_Adapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

    }
}
