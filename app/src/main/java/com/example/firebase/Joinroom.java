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

import java.util.Stack;

public class Joinroom extends AppCompatActivity {
    private Boolean f1 = false, f2 = false;
    private Button joincode, goback;
    private String code, yo , yo2;
    private EditText editText;
    private FirebaseAuth auth;
    private String id;
    private TextView tx;
    private String id2;
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
                if (code.length() == 5)
                {
                    DatabaseReference ref6 = FirebaseDatabase.getInstance().getReference("temp").child(code);
                    ref6.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists())
                                id2 = snapshot.getValue().toString();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("Rooms").child(code).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists() && !id.equals(id2))
                            {
                                joincode.setEnabled(false);
                                goback.setEnabled(false);
                                tx.setText("     Joining room!");
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Rooms").child(code);
                                reference.removeValue();
                                FirebaseDatabase Node = FirebaseDatabase.getInstance();
                                DatabaseReference rik = Node.getReference("waitroom");
                                rik.child(code).setValue(id);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseReference rop = FirebaseDatabase.getInstance().getReference("Match").child(code);
                                        rop.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists() && !f1)
                                                {
                                                    f1 = true;
                                                    yo = snapshot.getValue().toString();
                                                    Integer a = Integer.parseInt(yo);
                                                    int b = a;
                                                    DatabaseReference rop2 = FirebaseDatabase.getInstance().getReference("Match").child(code+"1");
                                                    rop2.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if (snapshot.exists()&& !f2)
                                                            {
                                                                f2 = true;
                                                               yo2 = snapshot.getValue().toString();
                                                               Integer a2 = Integer.parseInt(yo2);
                                                               int b2 = a2;
                                                               Intent intent = new Intent(Joinroom.this, Vs.class);
                                                               intent.putExtra("code",code);
                                                               intent.putExtra("id1", id);
                                                               intent.putExtra("id2", id2);
                                                               intent.putExtra("ran",b);
                                                               intent.putExtra("ran2",b2);
                                                               startActivity(intent);
                                                               finish();
                                                            }
                                                        }
                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                        }
                                                    });
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }
                                },850);
                            }
                            else if (id.equals(id2))
                            {
                                Toast.makeText(getApplicationContext(), "Can't play against the same player!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Invalid code!", Toast.LENGTH_SHORT).show();
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