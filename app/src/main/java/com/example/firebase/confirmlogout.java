package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                    stopService(new Intent(confirmlogout.this,MusicService.class));
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
    @Override
    public void onBackPressed() {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.odot, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.odot)
        {
            Intent intent1 = new Intent(confirmlogout.this,odotcoder.class);
            intent1.putExtra("keys","logout");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(confirmlogout.this,odotproject.class);
            intent1.putExtra("keys","logout");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}