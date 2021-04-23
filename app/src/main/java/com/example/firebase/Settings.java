package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Set;

public class Settings extends AppCompatActivity {
    private Button change_name;
    private Switch music, busy;
    private ImageView goback;
    private FirebaseAuth auth;
    private String id;
    private Boolean f1 = false;
    private Boolean f2 = false;
    private Boolean d = true;
    private Boolean d2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();
        change_name = findViewById(R.id.btn_changename);
        goback = findViewById(R.id.setting_gogack);
        music = findViewById(R.id.switch_music);
        busy = findViewById(R.id.switch_dontdisturb);
        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Taken");
        r.child(id).child("music").addValueEventListener(new ValueEventListener() { // בודק אם שינית בעבר
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!f1)
                {
                    f1 =true;
                    if (snapshot.exists())
                    {
                        d = false;
                        music.setChecked(true);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        r.child(id).child("busy").addValueEventListener(new ValueEventListener() {// בודק אם שינית בעבר
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!f2)
                {
                    f2 = true;
                    if (snapshot.exists())
                    {
                        d2 = false;
                        busy.setChecked(true);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && d)
                    {
                        if (!isMyServiceRunning(MusicService.class))
                            startService(new Intent(Settings.this,MusicService.class));
                        DatabaseReference set = FirebaseDatabase.getInstance().getReference("Taken");
                        set.child(id).child("music").setValue("");
                        Toast.makeText(getApplicationContext(),"Music running!", Toast.LENGTH_SHORT).show();
                    }
                    else if (!isChecked)
                    {
                        d = true;
                        stopService(new Intent(Settings.this,MusicService.class));
                        DatabaseReference rem = FirebaseDatabase.getInstance().getReference("Taken");
                        rem.child(id).child("music").removeValue();
                        Toast.makeText(getApplicationContext(),"Music stopped!", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        busy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (busy.isChecked() && d2)
                {
                    Toast.makeText(getApplicationContext(),"Do not disturb- on!", Toast.LENGTH_SHORT).show();
                    DatabaseReference set2 = FirebaseDatabase.getInstance().getReference("Taken");
                    set2.child(id).child("busy").setValue("");
                }
                else if(!isChecked)
                {
                    d2 = true;
                    DatabaseReference rem = FirebaseDatabase.getInstance().getReference("Taken");
                    rem.child(id).child("busy").removeValue();
                    Toast.makeText(getApplicationContext(),"Do not disturb- off!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, ChangeName.class));
                finish();
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, Homescreen.class));
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
}