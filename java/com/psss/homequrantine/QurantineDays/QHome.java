package com.psss.homequrantine.QurantineDays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.psss.homequrantine.Model.QDaysDB;
import com.psss.homequrantine.R;

public class QHome extends AppCompatActivity {

    CardView day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference checkRef, repRef;

    Toolbar toolbar;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qhome);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Qurantine Days");
        setSupportActionBar(toolbar);

        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);
        day6 = findViewById(R.id.day6);
        day7 = findViewById(R.id.day7);
        day8 = findViewById(R.id.day8);
        day9 = findViewById(R.id.day9);
        day10 = findViewById(R.id.day10);
        day11 = findViewById(R.id.day11);
        day12 = findViewById(R.id.day12);
        day13 = findViewById(R.id.day13);
        day14 = findViewById(R.id.day14);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        checkRef = database.getReference();
        repRef = database.getReference("Reports");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait a moment");
        progressDialog.setCancelable(false);

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/0")) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(QHome.this, Day1.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Completed", "NO");
                            bundle.putString("Report", "NIL");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {

                            repRef.child(mAuth.getUid()).child("Day 1").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(QHome.this, Day1.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Completed", "YES");
                                    bundle.putString("Report", qDaysDB.getReport());
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/0")) {

                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 1 activities first", Toast.LENGTH_SHORT).show();

                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/1")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day2.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 2").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day2.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/1")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 2 activities first", Toast.LENGTH_SHORT).show();
                        } else {
                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/2")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day3.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 3").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        assert qDaysDB != null;
                                        String report = qDaysDB.getReport();
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day3.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", report);
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/2")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 3 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/3")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day4.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 4").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day4.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/3")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 4 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/4")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day5.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 5").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day5.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/4")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 5 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/5")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day6.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 6").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day6.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/5")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 6 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/6")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day7.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 7").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day7.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/6")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 7 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/7")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day8.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 8").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day8.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/7")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 8 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/8")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day9.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 9").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day9.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/8")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 9 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/9")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day10.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 10").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day10.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/9")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 10 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/10")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day11.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 11").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day11.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/10")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 11 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/11")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day12.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 12").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day12.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/11")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 12 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/12")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day13.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 13").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day13.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        day14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                checkRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/12")) {
                            progressDialog.dismiss();
                            Toast.makeText(QHome.this, "Kindly complete the Day 13 activities first", Toast.LENGTH_SHORT).show();
                        } else {

                            if (snapshot.hasChild("14 Days Qurantine/" + mAuth.getUid() + "/13")) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(QHome.this, Day14.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("Completed", "NO");
                                bundle.putString("Report", "NIL");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                repRef.child(mAuth.getUid()).child("Day 14").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        QDaysDB qDaysDB = snapshot.getValue(QDaysDB.class);
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(QHome.this, Day14.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Completed", "YES");
                                        bundle.putString("Report", qDaysDB.getReport());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}
