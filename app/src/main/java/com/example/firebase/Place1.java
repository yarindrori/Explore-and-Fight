package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private String temp, id_current, id_op, id, id_combine, delete;
    private int score_current,score_op,score1, score2;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place1);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String id = user.getUid();
        score_current = 0;
        score_op = 0;
        spain  = findViewById(R.id.place1_answer1);
        london = findViewById(R.id.place1_answer2);
        africa = findViewById(R.id.place1_answer3);
        israel = findViewById(R.id.place1_answer4);
        tex = findViewById(R.id.textView_showtime);
        Intent intent = getIntent();
        id_current = intent.getStringExtra("id1");
        id_op = intent.getStringExtra("id2");
        id_combine = id_current + id_op;
        delete = id_op + id_current;
        FirebaseDatabase Node = FirebaseDatabase.getInstance();
        DatabaseReference reference = Node.getReference("Games");
        reference.child(id_combine).child(id_current).setValue(score_current);
        reference.child(id_combine).child(id_op).setValue(score_op);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Games").child(delete);
        ref.removeValue();
        spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spain.setBackgroundResource(R.drawable.roundwrong);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);

            }
        });
        london.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                london.setBackgroundResource(R.drawable.roundwrong);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);

            }
        });
        africa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                africa.setBackgroundResource(R.drawable.roundwrong);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);

            }
        });
        israel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                israel.setBackgroundResource(R.drawable.roundright);
                africa.setEnabled(false);
                israel.setEnabled(false);
                london.setEnabled(false);
                spain.setEnabled(false);
                score_current = score_current + 1;
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Games").child(id+id_op);
                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            reference.child(id+id_op).child(id_current).setValue(score_current);
                        }
                        else
                        {
                            reference.child(id_op+id).child(id_current).setValue(score_current);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        countDownTimer = new CountDownTimer(25000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                temp = String.valueOf((millisUntilFinished/1000));
                tex.setText(" " + temp);
            }

            @Override
            public void onFinish() {
                israel.setBackgroundResource(R.drawable.roundright);



            }
        };
        countDownTimer.start();










    }
}