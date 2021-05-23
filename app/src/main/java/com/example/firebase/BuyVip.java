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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuyVip extends AppCompatActivity {
    private Button Buy;
    private ImageView goback;
    private FirebaseAuth auth;
    private String id;
    private Boolean f = false;
    private Boolean f1 = false;
    private Boolean f2 = false;
    private Boolean f3 = false;
    private Boolean f4 =false ;
    private Boolean f5 = false;
    private Boolean f6 = false;
    private Boolean f7 =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_vip);
        auth = FirebaseAuth.getInstance();
        Buy = findViewById(R.id.button7);
        goback = findViewById(R.id.imageView5);
        id = auth.getCurrentUser().getUid();
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyVip.this, Shop.class));
                finish();
            }
        });
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = false;
                f1 = false;
                f2 =false;
                f3 =false;
                f4 = false;
                f5 = false;
                f6 = false;
                f7 = false;
                DatabaseReference rop = FirebaseDatabase.getInstance().getReference("VIP Users").child(id);
                rop.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists() && !f4)
                        {
                            f4 = true;
                            if (!f5) {
                                Toast.makeText(getApplicationContext(), "You are already VIP!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            DatabaseReference check = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
                            check.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists() && !f)
                                    {
                                        f = true;
                                        String c = snapshot.getValue().toString();
                                        int ci = Integer.parseInt(c);
                                        if (ci>=150)
                                        {
                                            f5 = true;
                                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
                                            ref.setValue(ci-150);
                                            String mail = auth.getCurrentUser().getEmail();
                                            DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users").child(id).child("password");
                                            r.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if (snapshot.exists() && !f1)
                                                    {
                                                        f1 = true;
                                                        String pass = snapshot.getValue().toString();
                                                        DatabaseReference r2 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("coins");
                                                        r2.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                                                if(snapshot1.exists() && !f2)
                                                                {
                                                                    f2 = true;
                                                                    String coins = snapshot1.getValue().toString();
                                                                    DatabaseReference r3 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("points");
                                                                    r3.addValueEventListener(new ValueEventListener() {

                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                                                            if (snapshot2.exists() && !f3)
                                                                            {
                                                                                f3 = true;
                                                                                String points = snapshot2.getValue().toString();
                                                                                DatabaseReference r4 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("username");
                                                                                r4.addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                        if (snapshot.exists() && !f6)
                                                                                        {
                                                                                            f6 = true;
                                                                                            String name = snapshot.getValue().toString();
                                                                                            Vipuser vip = new Vipuser(mail,pass,Integer.parseInt(coins),Integer.parseInt(points),name,true);
                                                                                            DatabaseReference v = FirebaseDatabase.getInstance().getReference("VIP Users");
                                                                                            v.child(id).setValue(vip); // שם את העצם בפיירבייס
                                                                                            DatabaseReference v1 = FirebaseDatabase.getInstance().getReference("Users");
                                                                                            v1.child(id).setValue(vip); // שם את העצם בפיירבייס
                                                                                            // vip.getUsername() משתמש בפעולה של user .
                                                                                            Toast.makeText(getApplicationContext(),vip.getUsername() + ", " +"You are now VIP!", Toast.LENGTH_SHORT).show();

                                                                                            DatabaseReference vr = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                                                            vr.addValueEventListener(new ValueEventListener() {
                                                                                                @Override
                                                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                                    if (snapshot.exists() && !f7)
                                                                                                    {
                                                                                                        f7 = true;
                                                                                                        int c = Integer.parseInt(snapshot.getValue().toString());
                                                                                                        DatabaseReference v2 = FirebaseDatabase.getInstance().getReference("fif").child(id);
                                                                                                        v2.setValue(c+10);
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
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
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
            Intent intent1 = new Intent(BuyVip.this,odotcoder.class);
            intent1.putExtra("keys","vip");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(BuyVip.this,odotproject.class);
            intent1.putExtra("keys","vip");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}