package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Joinroom extends AppCompatActivity {
    private Button joincode, goback;
    private String code;
    private EditText editText;
    private FirebaseAuth auth;
    private String id;
    private TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinroom);
        joincode = findViewById(R.id.join_room10);
        editText = findViewById(R.id.jointhis);
        goback = findViewById(R.id.button5);
        tx = findViewById(R.id.join_info);
        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Joinroom.this, Homescreen.class));
                finish();
            }
        });
        joincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editText.getText().toString();
                if (code.length() == 6)
                {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("Rooms").child(code).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                tx.setText("     Joining room!");
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Rooms").child(code);
                                reference.removeValue();
                                FirebaseDatabase Node = FirebaseDatabase.getInstance();
                                DatabaseReference rik = Node.getReference("waitroom");
                                rik.child(code).setValue(id);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(Joinroom.this, Vs.class));
                                        finish();
                                    }
                                },5000);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Invalid code!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid code!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}