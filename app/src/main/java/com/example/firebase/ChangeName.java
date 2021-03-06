package com.example.firebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
    private Boolean f= false;
    private Boolean f2 = false;
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
                    if ((rn.length() > 2 && rn.length() < 9) && rn.matches(".*[a-z].*"))
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
                        if (!rn.matches(".*[a-z].*"))
                        {
                            Toast.makeText(getApplicationContext(), "Invalid username!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Username must be between 3-8 char long!", Toast.LENGTH_SHORT).show();
                        }
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
                                    if ((rn.length() > 2 && rn.length() < 9) && rn.matches(".*[a-z].*"))
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
                                        if (!rn.matches(".*[a-z].*"))
                                        {
                                            Toast.makeText(getApplicationContext(), "Invalid username!", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(), "Username must be between 3-8 char long!", Toast.LENGTH_SHORT).show();
                                        }
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
            Intent intent1 = new Intent(ChangeName.this,odotcoder.class);
            intent1.putExtra("keys","name");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(ChangeName.this,odotproject.class);
            intent1.putExtra("keys","name");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}