package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class odotproject extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odotproject);
        btn = (Button) findViewById(R.id.button2);
        Intent intent1 = getIntent();
        String y = intent1.getStringExtra("keys");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (y.equals("login"))
                    {
                        startActivity(new Intent(odotproject.this, LoginActivity.class));
                        finish();
                    }
                    if (y.equals("signup"))
                    {
                        startActivity(new Intent(odotproject.this, HomeActivity.class));
                        finish();
                    }
                    if (y.equals("home"))
                    {
                        startActivity(new Intent(odotproject.this, Homescreen.class));
                        finish();
                    }
                    if (y.equals("leader"))
                    {
                        startActivity(new Intent(odotproject.this, Leaderboards.class));
                        finish();
                    }
                    if (y.equals("logout"))
                    {
                        startActivity(new Intent(odotproject.this, confirmlogout.class));
                        finish();
                    }
                    if (y.equals("shop"))
                    {
                        startActivity(new Intent(odotproject.this, Shop.class));
                        finish();
                    }
                    if (y.equals("fif"))
                    {
                        startActivity(new Intent(odotproject.this, fif.class));
                        finish();
                    }
                    if (y.equals("vip"))
                    {
                        startActivity(new Intent(odotproject.this, BuyVip.class));
                        finish();
                    }
                    if (y.equals("settings"))
                    {
                        startActivity(new Intent(odotproject.this, Settings.class));
                        finish();
                    }
                    if (y.equals("name"))
                    {
                        startActivity(new Intent(odotproject.this, ChangeName.class));
                        finish();
                    }
                    if (y.equals("play"))
                    {
                        startActivity(new Intent(odotproject.this, Createorjoin.class));
                        finish();
                    }
                    if (y.equals("join"))
                    {
                        startActivity(new Intent(odotproject.this, Joinroom.class));
                        finish();
                    }
                    if (y.equals("create"))
                    {
                        startActivity(new Intent(odotproject.this, Waiting.class));
                        finish();
                    }
                }
                catch (Exception e)
                {
                    startActivity(new Intent(odotproject.this,MainActivity.class));
                    finish();
                }

            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}