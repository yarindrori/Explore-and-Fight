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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Shop extends AppCompatActivity {
    private ImageView vip;
    private ImageView fif;
    private TextView coins;
    private Button goback;
    private FirebaseAuth auth;
    private String id;
    private Boolean f = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        vip = findViewById(R.id.image_vip);
        coins = findViewById(R.id.coins);
        goback = findViewById(R.id.button6);
        fif = findViewById(R.id.image_fif);
        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();
        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && !f)
                {
                    f = true;
                    String c = snapshot.getValue().toString();
                    coins.setText(c);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        fif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop.this, fif.class));
                finish();
            }
        });
        vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop.this, BuyVip.class));
                finish();
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop.this, Homescreen.class));
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
            Intent intent1 = new Intent(Shop.this,odotcoder.class);
            intent1.putExtra("keys","shop");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(Shop.this,odotproject.class);
            intent1.putExtra("keys","shop");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}