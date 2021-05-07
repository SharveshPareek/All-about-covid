package com.psss.homequrantine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.psss.homequrantine.Adapter.QPListAdapter;
import com.psss.homequrantine.Model.UploadDB;

import java.util.ArrayList;
import java.util.List;

public class HQPeopleList extends AppCompatActivity {

    String pincodeInput;

    ListView pplList;

    Toolbar toolbar;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference QPref;

    FirebaseListAdapter adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hqpeople_list);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Qurantine People in your area");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        pincodeInput = bundle.getString("Pincode");

        pplList = findViewById(R.id.hq_people_list);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        QPref = database.getReference("Qurantine Peoples");

        progressDialog = new ProgressDialog(this);

        Query querry = FirebaseDatabase.getInstance().getReference().child("Qurantine Peoples").child(pincodeInput);
        FirebaseListOptions<UploadDB> options = new FirebaseListOptions.Builder<UploadDB>()
                .setLayout(R.layout.hq_ppl_list_model).setQuery(querry, UploadDB.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView name = v.findViewById(R.id.tv_name);

                UploadDB uploadDB = (UploadDB) model;
                name.setText(uploadDB.getName());

            }
        };

        pplList.setAdapter(adapter);

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HQPeopleList.this,R.style.AlertDialogStyle);
                    builder.setMessage("Oops!! No Internet Connection").setCancelable(false)
                            .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
                                        Toast.makeText(HQPeopleList.this, "No Internet Connection!!", Toast.LENGTH_LONG).show();
                                        pullToRefresh.setRefreshing(false);
                                    }else{
                                        recreate();
                                    }
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else{
                    recreate();
                    pullToRefresh.setRefreshing(false);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HQPeopleList.this,Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
