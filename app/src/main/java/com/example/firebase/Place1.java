package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Place1 extends AppCompatActivity {
    private Button spain, london,africa,israel;
    private CountDownTimer countDownTimer;
    private TextView tex;
    private String code , id_current, id_op, id, id_combine, delete,score1, score2, score_current,temp , c;
    private FirebaseAuth auth;
    private Boolean flag = false;
    private Boolean flag2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place1);
        c = "";
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String id = user.getUid();
        spain  = findViewById(R.id.place1_answer1);
        london = findViewById(R.id.place1_answer2);
        africa = findViewById(R.id.place1_answer3);
        israel = findViewById(R.id.place1_answer4);
        tex = findViewById(R.id.textView_showtime);
        Intent intent = getIntent();
        id_current = id;
        code = intent.getStringExtra("code");
        id_op = intent.getStringExtra("id2");
        id_combine = id_current + id_op;
        delete = id_op + id_current;
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
        spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = "spain";
                spain.setBackgroundResource(R.drawable.roundwait);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);
            }
        });
        london.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = "london";
                london.setBackgroundResource(R.drawable.roundwait);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);
            }
        });
        africa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = "africa";
                africa.setBackgroundResource(R.drawable.roundwait);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);
            }
        });
        israel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = "israel";
                israel.setBackgroundResource(R.drawable.roundwait);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);
                DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                ref2.setValue("1");
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
                if (c.equals("africa"))
                {
                    africa.setBackgroundResource(R.drawable.roundwrong);
                    DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                    ref3.setValue("0");
                }
                if (c.equals("london"))
                {
                    london.setBackgroundResource(R.drawable.roundwrong);
                    DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                    ref3.setValue("0");
                }
                if (c.equals("spain"))
                {
                    spain.setBackgroundResource(R.drawable.roundwrong);
                    DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                    ref3.setValue("0");
                }
                if (c.equals(""))
                {
                    DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                    ref3.setValue("0");
                }
                c = "";
                israel.setBackgroundResource(R.drawable.roundright);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("Games").child(code).child(id_current);
                        reference5.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists())
                                {
                                    score1 = snapshot.getValue().toString();
                                    if(score1.equals("1"))
                                    {
                                        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_current);
                                        ref3.setValue("1");
                                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Score").child(code).child(id_op);
                                        ref4.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists())
                                                {
                                                    score2 = snapshot.getValue().toString();
                                                    if (score2.equals("1") && !flag2)
                                                    {
                                                        flag2 = true;
                                                        startActivity(new Intent(Place1.this, Tie.class));
                                                        finish();
                                                    }
                                                    else if (score2.equals("0") && !flag2)
                                                    {
                                                        flag2 = true;
                                                        startActivity(new Intent(Place1.this, Win.class));
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
                                        startActivity(new Intent(Place1.this, Lost.class));
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
    @Override
    public void onBackPressed() {

    }
}