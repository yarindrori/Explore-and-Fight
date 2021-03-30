package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Homescreen extends AppCompatActivity {
    private Button logout;
    private ImageView vs, ld;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private TextView tx;
    private String name = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        logout = findViewById(R.id.logout_btn);
        vs = findViewById(R.id.vsimg);
        tx = findViewById(R.id.player);
        ld = findViewById(R.id.ldimg);
        String id = user.getUid();


        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("username").getValue().toString();
                tx.setText(name + "!");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homescreen.this, confirmlogout.class));
                finish();

            }
        });
        vs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homescreen.this, Createorjoin.class));
                finish();
            }
        });
        ld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public void onBackPressed() {

    }


}