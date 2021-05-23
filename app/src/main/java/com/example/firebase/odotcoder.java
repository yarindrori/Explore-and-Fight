package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class odotcoder extends AppCompatActivity {
    private Button button;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odotcoder);
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent1 = getIntent();
                    String y = intent1.getStringExtra("keys");
                    if (y.equals("login"))
                    {
                        startActivity(new Intent(odotcoder.this,LoginActivity.class));
                        finish();
                    }
                    if (y.equals("signup"))
                    {
                        startActivity(new Intent(odotcoder.this, HomeActivity.class));
                        finish();
                    }
                    if (y.equals("home"))
                    {
                        startActivity(new Intent(odotcoder.this, Homescreen.class));
                        finish();
                    }
                    if (y.equals("leader"))
                    {
                        startActivity(new Intent(odotcoder.this, Leaderboards.class));
                        finish();
                    }
                    if (y.equals("logout"))
                    {
                        startActivity(new Intent(odotcoder.this, confirmlogout.class));
                        finish();
                    }
                    if (y.equals("shop"))
                    {
                        startActivity(new Intent(odotcoder.this, Shop.class));
                        finish();
                    }
                    if (y.equals("fif"))
                    {
                        startActivity(new Intent(odotcoder.this, fif.class));
                        finish();
                    }
                    if (y.equals("vip"))
                    {
                        startActivity(new Intent(odotcoder.this, BuyVip.class));
                        finish();
                    }
                    if (y.equals("settings"))
                    {
                        startActivity(new Intent(odotcoder.this, Settings.class));
                        finish();
                    }
                    if (y.equals("name"))
                    {
                        startActivity(new Intent(odotcoder.this, ChangeName.class));
                        finish();
                    }
                    if (y.equals("play"))
                    {
                        startActivity(new Intent(odotcoder.this, Createorjoin.class));
                        finish();
                    }
                    if (y.equals("join"))
                    {
                        startActivity(new Intent(odotcoder.this, Joinroom.class));
                        finish();
                    }
                    if (y.equals("create"))
                    {
                        startActivity(new Intent(odotcoder.this, Waiting.class));
                        finish();
                    }
                }
                catch (Exception e)
                {
                    startActivity(new Intent(odotcoder.this,MainActivity.class));
                    finish();
                }

            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}