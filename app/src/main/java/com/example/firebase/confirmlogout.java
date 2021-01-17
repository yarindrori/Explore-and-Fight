package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class confirmlogout extends AppCompatActivity {
    private Button confirm_logout, cancel;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmlogout);
        confirm_logout = findViewById(R.id.logout_btn_final);
        cancel = findViewById(R.id.cancel_logout);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        confirm_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null) {
                    Toast.makeText(getApplicationContext(), "Logout from:" + " " + user.getEmail(), Toast.LENGTH_LONG).show();
                    auth.signOut();
                    startActivity(new Intent(confirmlogout.this, LoginActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(confirmlogout.this, LoginActivity.class));
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirmlogout.this, Homescreen.class));
                finish();
            }
        });

    }
}