package com.psss.homequrantine.BeforeCovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.psss.homequrantine.R;

public class Bc_vaccination_start extends AppCompatActivity {

    ImageView website, umang, ayurevedha;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bc_vaccination_start);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Vaccination");
        setSupportActionBar(toolbar);

        website = findViewById(R.id.website);
        umang = findViewById(R.id.umang);
        ayurevedha = findViewById(R.id.ayurevedhaapp);

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.cowin.gov.in/home");
            }
        });

        umang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://play.google.com/store/apps/details?id=in.gov.umang.negd.g2c.international&hl=en_IN&gl=US");
            }
        });

        ayurevedha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu&hl=en_IN&gl=US");
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}