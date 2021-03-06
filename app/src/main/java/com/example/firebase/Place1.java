package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Place1 extends AppCompatActivity {
    private ImageView fif;
    private Boolean f = false;
    private Boolean flag_noans = false;
    private Boolean flme = false;
    private Boolean flop = false;
    private Button place1;
    private Button place2;
    private Button place3;
    private Button place4;
    private CountDownTimer countDownTimer;
    private TextView tex;
    private TextView count;
    private String code;
    private String id_current;
    private String id_op;
    private String id;
    private String id_combine;
    private String delete;
    private String score1;
    private String score2;
    private String score_current;
    private String temp;
    private String c;
    private FirebaseAuth auth;
    private Boolean flag = false;
    private Boolean flag2 = false;
    private Boolean f100 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place1);
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        int ran2 = intent.getIntExtra("ran2", 0);
        c = "";
        String[] l1 = new String[]{"Cambodia","India" ,"Thailand","Philippines"};
        String[] l2 = new String[]{"Sydney","Singapore" ,"Cape Town","Montreal"};
        String[] l3 = new String[]{"Paris","New Zealand" ,"Canada","Sweden"};
        String[] l4 = new String[]{"Peru","Brazil" ,"Africa","Portugal"};
        String[] l5 = new String[]{"Moscow","Ukraine" ,"Bulgaria","Czech Republic"};
        String[] l6 = new String[]{"Chile","Peru" ,"Costa Rica","Argentina"};
        String[] l7 = new String[]{"San Francisco","Los Angeles" ,"San Diego","United Kingdom"};
        String[] l8 = new String[]{"Israel","Egypt" ,"Spain","Turkey"};
        String[] l9 = new String[]{"Brazil","Colombia" ,"Chile","Ecuador"};
        String[] l10 = new String[]{"Greece","Israel" ,"Cyprus","Panama"};
        String[] l11 = new String[]{"Dubai","Bahrain" ,"Saudi Arabia","Morocco"};
        String[] l12 = new String[]{"India","Zimbabwe" ,"Zambia","Thailand"};

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String id = user.getUid();
        fif = findViewById(R.id.imageView14);
        count = findViewById(R.id.fif_info6);
        place1  = findViewById(R.id.place1_answer1);
        place2 = findViewById(R.id.place1_answer2);
        place3 = findViewById(R.id.place1_answer3);
        place4 = findViewById(R.id.place1_answer4);
        tex = findViewById(R.id.textView_showtime);
        id_current = id;
        id_op = intent.getStringExtra("id2");
        id_combine = id_current + id_op;
        delete = id_op + id_current;
        DatabaseReference y = FirebaseDatabase.getInstance().getReference("fif").child(id);
        y.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String howmuch = snapshot.getValue().toString();
                    count.setText(howmuch);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference refe = FirebaseDatabase.getInstance().getReference("Match").child(code);
        refe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && !f)
                {
                    f = true;
                    Random rn = new Random();
                    int num = rn.nextInt(4) + 1;
                    String chosen = snapshot.getValue().toString();
                    if (chosen.equals("1"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l1[0]);
                            place2.setText(l1[1]);
                            place3.setText(l1[2]);
                            place4.setText(l1[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l1[0]);
                            place1.setText(l1[1]);
                            place3.setText(l1[2]);
                            place4.setText(l1[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l1[0]);
                            place1.setText(l1[1]);
                            place2.setText(l1[2]);
                            place4.setText(l1[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l1[0]);
                            place1.setText(l1[1]);
                            place2.setText(l1[2]);
                            place3.setText(l1[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                flag_noans = true;
                                c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l1[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l1[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l1[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l1[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l1[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists() && !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists() && !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);
                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("2"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l2[0]);
                            place2.setText(l2[1]);
                            place3.setText(l2[2]);
                            place4.setText(l2[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l2[0]);
                            place1.setText(l2[1]);
                            place3.setText(l2[2]);
                            place4.setText(l2[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l2[0]);
                            place1.setText(l2[1]);
                            place2.setText(l2[2]);
                            place4.setText(l2[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l2[0]);
                            place1.setText(l2[1]);
                            place2.setText(l2[2]);
                            place3.setText(l2[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

                                                if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num ==1)
                                {
                                    if (c.equals(l2[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l2[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l2[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l2[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l2[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists() && !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists() && !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();

                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();

                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);
                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);

                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("3"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l3[0]);
                            place2.setText(l3[1]);
                            place3.setText(l3[2]);
                            place4.setText(l3[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l3[0]);
                            place1.setText(l3[1]);
                            place3.setText(l3[2]);
                            place4.setText(l3[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l3[0]);
                            place1.setText(l3[1]);
                            place2.setText(l3[2]);
                            place4.setText(l3[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l3[0]);
                            place1.setText(l3[1]);
                            place2.setText(l3[2]);
                            place3.setText(l3[3]);
                        }

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }


                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l3[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l3[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l3[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l3[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l3[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists() && !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists() && !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);


                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);
                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("4"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l4[0]);
                            place2.setText(l4[1]);
                            place3.setText(l4[2]);
                            place4.setText(l4[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l4[0]);
                            place1.setText(l4[1]);
                            place3.setText(l4[2]);
                            place4.setText(l4[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l4[0]);
                            place1.setText(l4[1]);
                            place2.setText(l4[2]);
                            place4.setText(l4[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l4[0]);
                            place1.setText(l4[1]);
                            place2.setText(l4[2]);
                            place3.setText(l4[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l4[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l4[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l4[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l4[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l4[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";


                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists() && !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);
                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("5"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l5[0]);
                            place2.setText(l5[1]);
                            place3.setText(l5[2]);
                            place4.setText(l5[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l5[0]);
                            place1.setText(l5[1]);
                            place3.setText(l5[2]);
                            place4.setText(l5[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l5[0]);
                            place1.setText(l5[1]);
                            place2.setText(l5[2]);
                            place4.setText(l5[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l5[0]);
                            place1.setText(l5[1]);
                            place2.setText(l5[2]);
                            place3.setText(l5[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                               c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l5[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l5[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l5[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l5[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l5[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()&& !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop )
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);


                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);


                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);






                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("6"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l6[0]);
                            place2.setText(l6[1]);
                            place3.setText(l6[2]);
                            place4.setText(l6[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l6[0]);
                            place1.setText(l6[1]);
                            place3.setText(l6[2]);
                            place4.setText(l6[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l6[0]);
                            place1.setText(l6[1]);
                            place2.setText(l6[2]);
                            place4.setText(l6[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l6[0]);
                            place1.setText(l6[1]);
                            place2.setText(l6[2]);
                            place3.setText(l6[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               flag_noans = true;
                               c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l6[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l6[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l6[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l6[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l6[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()&& !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);


                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);


                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);

                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);






                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("7"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l7[0]);
                            place2.setText(l7[1]);
                            place3.setText(l7[2]);
                            place4.setText(l7[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l7[0]);
                            place1.setText(l7[1]);
                            place3.setText(l7[2]);
                            place4.setText(l7[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l7[0]);
                            place1.setText(l7[1]);
                            place2.setText(l7[2]);
                            place4.setText(l7[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l7[0]);
                            place1.setText(l7[1]);
                            place2.setText(l7[2]);
                            place3.setText(l7[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               flag_noans = true;
                               c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l7[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l7[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l7[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l7[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l7[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()&& !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);

                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);






                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("8"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l8[0]);
                            place2.setText(l8[1]);
                            place3.setText(l8[2]);
                            place4.setText(l8[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l8[0]);
                            place1.setText(l8[1]);
                            place3.setText(l8[2]);
                            place4.setText(l8[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l8[0]);
                            place1.setText(l8[1]);
                            place2.setText(l8[2]);
                            place4.setText(l8[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l8[0]);
                            place1.setText(l8[1]);
                            place2.setText(l8[2]);
                            place3.setText(l8[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               flag_noans = true;
                               c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l8[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l8[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l8[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l8[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l8[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()&& !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);

                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);






                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("9"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l9[0]);
                            place2.setText(l9[1]);
                            place3.setText(l9[2]);
                            place4.setText(l9[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l9[0]);
                            place1.setText(l9[1]);
                            place3.setText(l9[2]);
                            place4.setText(l9[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l9[0]);
                            place1.setText(l9[1]);
                            place2.setText(l9[2]);
                            place4.setText(l9[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l9[0]);
                            place1.setText(l9[1]);
                            place2.setText(l9[2]);
                            place3.setText(l9[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               flag_noans = true;
                               c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l9[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l9[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l9[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l9[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l9[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists() && !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);

                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);





                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("10"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l10[0]);
                            place2.setText(l10[1]);
                            place3.setText(l10[2]);
                            place4.setText(l10[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l10[0]);
                            place1.setText(l10[1]);
                            place3.setText(l10[2]);
                            place4.setText(l10[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l10[0]);
                            place1.setText(l10[1]);
                            place2.setText(l10[2]);
                            place4.setText(l10[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l10[0]);
                            place1.setText(l10[1]);
                            place2.setText(l10[2]);
                            place3.setText(l10[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               flag_noans = true;
                               c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if(num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if(num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if(num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l10[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l10[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l10[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l10[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l10[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()&& !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);

                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);






                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("11"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l11[0]);
                            place2.setText(l11[1]);
                            place3.setText(l11[2]);
                            place4.setText(l11[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l11[0]);
                            place1.setText(l11[1]);
                            place3.setText(l11[2]);
                            place4.setText(l11[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l11[0]);
                            place1.setText(l11[1]);
                            place2.setText(l11[2]);
                            place4.setText(l11[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l11[0]);
                            place1.setText(l11[1]);
                            place2.setText(l11[2]);
                            place3.setText(l11[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               flag_noans = true;
                               c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l11[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l11[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l11[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l11[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l11[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()&& !flme)
                                                {

                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);

                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);






                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);

                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);






                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                    if (chosen.equals("12"))
                    {
                        if (num == 1)
                        {
                            place1.setText(l12[0]);
                            place2.setText(l12[1]);
                            place3.setText(l12[2]);
                            place4.setText(l12[3]);
                        }
                        if (num == 2)
                        {
                            place2.setText(l12[0]);
                            place1.setText(l12[1]);
                            place3.setText(l12[2]);
                            place4.setText(l12[3]);
                        }
                        if (num == 3)
                        {
                            place3.setText(l12[0]);
                            place1.setText(l12[1]);
                            place2.setText(l12[2]);
                            place4.setText(l12[3]);
                        }
                        if (num == 4)
                        {
                            place4.setText(l12[0]);
                            place1.setText(l12[1]);
                            place2.setText(l12[2]);
                            place3.setText(l12[3]);
                        }
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Games").child(code).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists() && !flag)
                                {
                                    flag = true;
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Games").child(code);
                                    ref1.child(id_current).setValue(id_current);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        fif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (f100)
                                    Toast.makeText(getApplicationContext(),"Already used!",Toast.LENGTH_SHORT).show();
                                DatabaseReference yr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                yr.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists() && !f100)
                                        {
                                            f100 = true;
                                            String howmuch = snapshot.getValue().toString();
                                            count.setText(howmuch);
                                            int how = Integer.parseInt(howmuch);
                                            if (how > 0)
                                            {
                                                DatabaseReference yr2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                yr2.setValue(how - 1);
                                                Toast.makeText(getApplicationContext(),"50/50 used!",Toast.LENGTH_SHORT).show();
                                                if (num == 1)
                                                {
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }

 if (num == 2)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place4.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place4.setEnabled(false);
                                                }
                                                if (num == 3)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place2.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place2.setEnabled(false);
                                                }
                                                if (num == 4)
                                                {
                                                    place1.setBackgroundResource(R.drawable.roundwrong);
                                                    place3.setBackgroundResource(R.drawable.roundwrong);
                                                    place1.setEnabled(false);
                                                    place3.setEnabled(false);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(),"You don't have a 50/50!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        place1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place1.getText().toString();
                                place1.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 1)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place2.getText().toString();
                                place2.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 2)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
c = place3.getText().toString();
                                place3.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 3)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });
                        place4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                flag_noans = true;
                                c = place4.getText().toString();
                                place4.setBackgroundResource(R.drawable.roundwait);
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (num == 4)
                                {
                                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                    ref2.setValue("1");
                                }
                            }
                        });

                        countDownTimer = new CountDownTimer(15000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                temp = String.valueOf((millisUntilFinished/1000));
                                tex.setText(" " + temp);
                            }
                            @Override
                            public void onFinish() {
                                place1.setEnabled(false);
                                place2.setEnabled(false);
                                place3.setEnabled(false);
                                place4.setEnabled(false);
                                if (!flag_noans)
                                {
                                    DatabaseReference noans = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                    noans.setValue("0");
                                }
                                if (num == 1)
                                {
                                    if (c.equals(l12[1]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place1.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 2)
                                {
                                    if (c.equals(l12[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[2]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place2.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 3)
                                {
                                    if (c.equals(l12[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[3]))
                                    {
                                        place4.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place3.setBackgroundResource(R.drawable.roundright);
                                }
                                if (num == 4)
                                {
                                    if (c.equals(l12[1]))
                                    {
                                        place1.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[2]))
                                    {
                                        place2.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(l12[3]))
                                    {
                                        place3.setBackgroundResource(R.drawable.roundwrong);
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    if (c.equals(""))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("0");
                                    }
                                    place4.setBackgroundResource(R.drawable.roundright);
                                }
                                c = "";

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()&& !flme)
                                                {
                                                    flme = true;
                                                    score1 = snapshot.getValue().toString();
                                                    if(score1.equals("1"))
                                                    {
                                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                                        ref3.setValue("1");
                                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                                        ref4.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()&& !flop)
                                                                {
                                                                    flop = true;
                                                                    score2 = snapshot.getValue().toString();
                                                                    if (score2.equals("1") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);

                                                                       
                                                                       


                                                                      
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                    else if (score2.equals("0") && !flag2)
                                                                    {
                                                                        flag2 = true;
                                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                                        intent.putExtra("code",code);
                                                                        intent.putExtra("id1",id_current);
                                                                        intent.putExtra("id2",id_op);
                                                                        intent.putExtra("ran2", ran2);
                                                                        
                                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                                        ol.child(id).child("currentscore").setValue(1);

                                                                       
                                                                       


                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                            }
                                                        });
                                                    }
                                                    else
                                                    {
                                                        Intent intent = new Intent(Place1.this, Vs2.class);
                                                        intent.putExtra("code",code);
                                                        intent.putExtra("id1",id_current);
                                                        intent.putExtra("id2",id_op);
                                                        intent.putExtra("ran2", ran2);
                                                        DatabaseReference ol = FirebaseDatabase.getInstance().getReference("Users");
                                                        ol.child(id).child("currentscore").setValue(0);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }
                                },3550);
                            }
                        };
                        countDownTimer.start();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}