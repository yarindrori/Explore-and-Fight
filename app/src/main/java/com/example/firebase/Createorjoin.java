package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Createorjoin extends AppCompatActivity {
    private Button create, join, goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createorjoin);
        create = findViewById(R.id.create_room);
        join = findViewById(R.id.join_room);
        goback = findViewById(R.id.button3);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Createorjoin.this, Homescreen.class));
                finish();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Createorjoin.this, Waiting.class));
                finish();
            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Createorjoin.this, Joinroom.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}