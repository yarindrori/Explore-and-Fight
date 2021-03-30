package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WhoWon extends AppCompatActivity {
    private Boolean f = false;
    private TextView tex1, tex2, tex3;
    private ImageView win , lose, draw;
    private ProgressBar bar;
    private Button back;
    private FirebaseAuth auth;
    private String score, score2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_won);
        back = findViewById(R.id.back24);
        bar = findViewById(R.id.progressbar2);
        win = findViewById(R.id.imageView225);
        lose = findViewById(R.id.imageView224);
        draw = findViewById(R.id.imageView7);
        tex1 = findViewById(R.id.textView21);
        tex2 = findViewById(R.id.textView22);
        auth = FirebaseAuth.getInstance();
        // מסתיר הכל לפני תוצאה
        win.setVisibility(View.GONE);
        lose.setVisibility(View.GONE);
        draw.setVisibility(View.GONE);
        tex1.setVisibility(View.GONE);
        tex2.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        back.setEnabled(false);

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        String id = intent.getStringExtra("id1");
        String id_op = intent.getStringExtra("id2");

        DatabaseReference rp = FirebaseDatabase.getInstance().getReference("Games").child(code);
        rp.removeValue();
        DatabaseReference ro1 = FirebaseDatabase.getInstance().getReference("Score").child(code);
        ro1.removeValue();
        DatabaseReference ro2 = FirebaseDatabase.getInstance().getReference("Match").child(code);
        ro2.removeValue();



        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users").child(id_op).child("currentscore");
        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && !f)
                {
                    score = snapshot.getValue().toString();
                    DatabaseReference r2 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("currentscore");
                    r2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists() && !f)
                            {
                                f = true;
                                score2 = snapshot.getValue().toString();
                                Integer iscore = Integer.parseInt(score);
                                int sc = iscore; // יריב
                                Integer iscore2 = Integer.parseInt(score2);
                                int sc2 = iscore2; // אני
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        r.removeValue();
                                        r2.removeValue();
                                    }
                                },5000);
                                if (sc==sc2)
                                {
                                    bar.setVisibility(View.GONE);
                                    draw.setVisibility(View.VISIBLE); // מראה את התיקו
                                    back.setVisibility(View.VISIBLE);
                                    back.setEnabled(true);
                                    tex1.setVisibility(View.VISIBLE);
                                    tex2.setVisibility(View.VISIBLE);
                                    tex1.setText("Your score: +10 points");
                                    tex2.setText("Coins: +5 coins");

                                }
                                else
                                {
                                    if (sc > sc2)
                                    {
                                        bar.setVisibility(View.GONE);
                                        lose.setVisibility(View.VISIBLE); // מראה את ההפסד
                                        back.setVisibility(View.VISIBLE);
                                        back.setEnabled(true);
                                        tex1.setVisibility(View.VISIBLE);
                                        tex2.setVisibility(View.VISIBLE);
                                        tex1.setText("Your score: +5 points");
                                        tex2.setText("Coins: +1 coins");


                                    }
                                    else
                                    {
                                        bar.setVisibility(View.GONE);
                                        win.setVisibility(View.VISIBLE); // מראה את הניצחון
                                        back.setVisibility(View.VISIBLE);
                                        back.setEnabled(true);
                                        tex1.setVisibility(View.VISIBLE);
                                        tex2.setVisibility(View.VISIBLE);
                                        tex1.setText("Your score: +25 points");
                                        tex2.setText("Coins: +10 coins");
                                    }
                                }
                            }
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhoWon.this, Homescreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}