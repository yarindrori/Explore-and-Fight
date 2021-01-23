package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Win extends AppCompatActivity {
    private Button goback;
    private FirebaseAuth auth;
    private String score;
    private Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        goback = findViewById(R.id.cancel_logout2);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String id = user.getUid();
        DatabaseReference refe = FirebaseDatabase.getInstance().getReference("Users").child(id).child("points");
        refe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    if(flag == false)
                    {
                        flag = true;
                        score =  snapshot.getValue().toString();
                        Integer score_rl= Integer.parseInt(score);
                        int a = score_rl + 25;
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(id).child("points");
                        ref.setValue(a);
                        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Games");
                        r.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists())
                                {
                                   r.removeValue();
                                }
                                else
                                {
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                else
                {
                    DatabaseReference r = FirebaseDatabase.getInstance().getReference("Games");
                    r.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists())
                            {
                                r.removeValue();
                            }
                            else
                            {
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
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Win.this, Homescreen.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}