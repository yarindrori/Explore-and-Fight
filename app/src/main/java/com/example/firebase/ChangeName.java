package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeName extends AppCompatActivity {
    private FirebaseAuth auth;
    private ImageView goback;
    private Button confirm;
    private EditText edit_name;
    private String name = null;
    private Boolean f= false, f2 = false, f3 = false ,f4 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        auth = FirebaseAuth.getInstance();
        goback = findViewById(R.id.name_gogack);
        confirm = findViewById(R.id.btn_confirm);
        edit_name = findViewById(R.id.edit_name);
        String id = auth.getCurrentUser().getUid();
        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users");
        r.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    name = snapshot.child("username").getValue().toString();
                    edit_name.setText(name);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        DatabaseReference checkvip = FirebaseDatabase.getInstance().getReference("VIP Users").child(id);
        checkvip.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    f = true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String n = edit_name.getText().toString();
                    String rn = n.replaceAll(" ", "");
                    edit_name.setText(rn);
                    if ((rn.length() > 3 && rn.length() < 11))
                    {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("Names").child(rn).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (!f2)
                                {
                                    if(snapshot.exists()){
                                        if (!rn.equals(name))
                                        Toast.makeText(getApplicationContext(),"Username is taken!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        f2 = true;
                                        if (f)
                                        {
                                            DatabaseReference r3 = FirebaseDatabase.getInstance().getReference("VIP Users");
                                            r3.child(id).child("username").setValue(rn);
                                        }
                                        DatabaseReference r2 = FirebaseDatabase.getInstance().getReference("Users");
                                        r2.child(id).child("username").setValue(rn);
                                        ref.child("Names").child(name).removeValue();
                                        ref.child("Names").child(rn).setValue(rn);
                                        Toast.makeText(getApplicationContext(),"Username changed!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ChangeName.this, Settings.class));
                                        finish();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Username must be between 4-10 char long!", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edit_name.getText().toString().equals(name))
                {
                    AlertDialog dialog = new AlertDialog.Builder(ChangeName.this)
                            .setTitle("Are you sure?")
                            .setMessage("Do you want to save changes?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String n = edit_name.getText().toString();
                                    String rn = n.replaceAll(" ", "");
                                    edit_name.setText(rn);
                                    if ((rn.length() > 3 && rn.length() < 11))
                                    {
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                        ref.child("Names").child(rn).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (!f2)
                                                {
                                                    if(snapshot.exists()){
                                                        if (!rn.equals(name))
                                                        Toast.makeText(getApplicationContext(),"Username is taken!", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else
                                                    {
                                                        f2 = true;
                                                        if (f)
                                                        {
                                                            DatabaseReference r3 = FirebaseDatabase.getInstance().getReference("VIP Users");
                                                            r3.child(id).child("username").setValue(rn);
                                                        }
                                                        DatabaseReference r2 = FirebaseDatabase.getInstance().getReference("Users");
                                                        r2.child(id).child("username").setValue(rn);
                                                        ref.child("Names").child(name).removeValue();
                                                        ref.child("Names").child(rn).setValue(rn);
                                                        Toast.makeText(getApplicationContext(),"Username changed!", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(ChangeName.this, Settings.class));
                                                        finish();
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Username must be between 4-10 char long!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(ChangeName.this, Settings.class));
                                    finish();
                                }
                            })
                            .show();
                }
                else
                {
                    startActivity(new Intent(ChangeName.this, Settings.class));
                    finish();
                }

            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}