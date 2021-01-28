package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tie extends AppCompatActivity {
    private Button goback;
    private FirebaseAuth auth;
    private String score;
    private Boolean flag = false;
    private Boolean flag2 = false;
    private Boolean flag3 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tie);
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
                        int a = score_rl + 10;
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(id).child("points");
                        ref.setValue(a);
                    }
                }
                else
                {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        DatabaseReference reference10 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
        reference10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    if (flag2 == false)
                    {
                        flag2 = true;
                        score = snapshot.getValue().toString();
                        Integer score_rl= Integer.parseInt(score);
                        int a = score_rl + 5;
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
                        ref.setValue(a);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        goback = findViewById(R.id.cancel_logout3);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tie.this, Homescreen.class);
                intent.putExtra("key", "yes");
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}