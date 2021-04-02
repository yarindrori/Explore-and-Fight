package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Shop extends AppCompatActivity {
    private ImageView vip;
    private TextView coins;
    private Button goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        vip = findViewById(R.id.image_vip);
        coins = findViewById(R.id.coins);
        goback = findViewById(R.id.button6);
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
}