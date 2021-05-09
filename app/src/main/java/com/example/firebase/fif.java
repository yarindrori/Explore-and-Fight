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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fif extends AppCompatActivity {
    private Button buy50;
    private ImageView goback;
    private FirebaseAuth auth;
    private TextView tex;
    private String id;
    private Boolean f = false , f1 = false, f2 = false, f3 = false, f4 =false , f5 = false, f6 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fif);
        buy50 = findViewById(R.id.button8);
        auth = FirebaseAuth.getInstance();
        tex = findViewById(R.id.fif_info7);
        id = auth.getCurrentUser().getUid();
        DatabaseReference g = FirebaseDatabase.getInstance().getReference("fif").child(id);
        g.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot4) {
                if (snapshot4.exists() && !f6)
                {
                    tex.setText("You have:"+" " + snapshot4.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        goback = findViewById(R.id.imageView11);
        buy50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = false;
                f1 = false;
                DatabaseReference check = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
                check.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists() && !f)
                        {
                            f = true;
                            String c = snapshot.getValue().toString();
                            int ci = Integer.parseInt(c);
                            if (ci>=20)
                            {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
                                ref.setValue(ci-20);
                                DatabaseReference fop = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                fop.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                        if (snapshot1.exists() && !f1)
                                        {
                                            f1 = true;
                                            String fp = snapshot1.getValue().toString();
                                            int set = Integer.parseInt(fp);
                                            DatabaseReference fop2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                            fop2.setValue(set + 1);
                                            Toast.makeText(getApplicationContext(),"You have bought a 50/50 chance!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Not enough coins!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fif.this, Shop.class));
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
            Intent intent1 = new Intent(fif.this,odotcoder.class);
            intent1.putExtra("keys","fif");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(fif.this,odotproject.class);
            intent1.putExtra("keys","fif");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}