package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homescreen extends AppCompatActivity {
    private Button logout;
    private ImageView vs, ld, shop;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private TextView tx;
    private String name = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        logout = findViewById(R.id.logout_btn);
        vs = findViewById(R.id.vsimg);
        shop = findViewById(R.id.image_shop);
        tx = findViewById(R.id.player);
        ld = findViewById(R.id.ldimg);
        String id = user.getUid();
        try {
            Intent intent1 = getIntent();
            String y = intent1.getStringExtra("key");
            String code = intent1.getStringExtra("code");
            String id1 = intent1.getStringExtra("id1");
            String id_op = intent1.getStringExtra("id2");
            if (y.equals("yes"))
            {
                DatabaseReference rp = FirebaseDatabase.getInstance().getReference("Games").child(code);
                rp.removeValue();
                DatabaseReference ro1 = FirebaseDatabase.getInstance().getReference("Score").child(code);
                ro1.removeValue();
                DatabaseReference ro2 = FirebaseDatabase.getInstance().getReference("Match").child(code);
                ro2.removeValue();
                DatabaseReference ro3 = FirebaseDatabase.getInstance().getReference("Match").child(code+"1");
                ro3.removeValue();
                DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users").child(id1).child("currentscore");
                r.removeValue();
                DatabaseReference r2= FirebaseDatabase.getInstance().getReference("Users").child(id_op).child("currentscore");
                r2.removeValue();
            }
        }
        catch (Exception e)
        {}

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("username").getValue().toString();
                tx.setText(name + "!");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homescreen.this, confirmlogout.class));
                finish();
            }
        });
       shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homescreen.this, Shop.class));
                finish();
            }
        });
        vs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homescreen.this, Createorjoin.class));
                finish();
            }
        });
        ld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}