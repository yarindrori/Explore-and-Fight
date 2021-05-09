package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ImageView vs, ld, shop, vop, settings;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private TextView tx;
    private String name = null;
    private Boolean f = false;
    private Boolean f1 = false, f2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        settings = findViewById(R.id.img_settings);
        logout = findViewById(R.id.logout_btn);
        vs = findViewById(R.id.vsimg);
        vop = findViewById(R.id.imageView13);
        shop = findViewById(R.id.image_shop);
        tx = findViewById(R.id.player);
        ld = findViewById(R.id.ldimg);
        String id = user.getUid();
        vop.setVisibility(View.GONE);

        DatabaseReference taken = FirebaseDatabase.getInstance().getReference("Taken");
        taken.child(id).child("music").addValueEventListener(new ValueEventListener() { // מפעיל מוזיקה
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!f1)
                {
                    f1 =true;
                    if (snapshot.exists())
                    {
                        if (!isMyServiceRunning(MusicService.class))
                        startService(new Intent(Homescreen.this,MusicService.class));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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
        // מראה שם מוזהב ותג vip
        DatabaseReference r = FirebaseDatabase.getInstance().getReference("VIP Users").child(id);
        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && !f)
                {
                    f = true;
                    Vipuser vipuser = new Vipuser(snapshot.child("mail").getValue().toString(),snapshot.child("password").getValue().toString(),Integer.parseInt(snapshot.child("coins").getValue().toString()),Integer.parseInt(snapshot.child("points").getValue().toString()),snapshot.child("username").getValue().toString(), (Boolean)snapshot.child("access").getValue());
                    if (vipuser.getAccess() == true)
                    {
                        tx.setTextColor(getResources().getColor(R.color.gold));
                        vop.setVisibility(View.VISIBLE);
                    }
                    else
                        {
                        // אתה לא VIP ואין צורך להציג מוזהב ותג vip
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homescreen.this, Settings.class));
                finish();
            }
        });
        ld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homescreen.this, Leaderboards.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
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
            Intent intent1 = new Intent(Homescreen.this,odotcoder.class);
            intent1.putExtra("keys","home");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(Homescreen.this,odotproject.class);
            intent1.putExtra("keys","home");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}