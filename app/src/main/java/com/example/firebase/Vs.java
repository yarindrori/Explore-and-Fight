package com.example.firebase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Vs extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    private LatLng l1 = new LatLng(13.41241963977408,103.8681012543761); // Cambodia
    private LatLng l2 = new LatLng(-33.85803087516396,151.21450292817096); // Sydney
    private LatLng l3 = new LatLng(48.86060891918641,2.2908821980026883); // Paris
    private LatLng l4 = new LatLng(-13.165033540843705,-72.54456280018185); // Peru
    private LatLng l5 = new LatLng(55.75304029974323,37.62224150108071); // Moscow
    private LatLng l6 = new LatLng(-27.125633026348023,-109.27719045831813); // Chile
    private LatLng l7 = new LatLng(37.80840312108457,-122.47068758882803); // San Francisco
    private LatLng l8 = new LatLng(31.77657664705524 ,35.23371285289221); // Jerusalem
    private LatLng l9 = new LatLng(-22.95187921136084,-43.21021611615836); // Brazil
    private LatLng l10 = new LatLng(36.46172868565078 ,25.37786852173211); // Greece
    private LatLng l11 = new LatLng(25.19600810188601 ,55.27520186162982); // Dubai
    private LatLng l12 = new LatLng(27.17278518287194 ,78.0422489679255); // India
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private Random rn = new Random();
    private int num = rn.nextInt(12) + 1;
    private int b = 0;
    private TextView tex,tex1;
    private String a;
    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimer1;
    private Boolean f = false;
    private static final Integer PANORAMA_CAMERA_DURATION = 1000;
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String STREET_VIEW_BUNDLE = "StreetViewBundle";
    private StreetViewPanorama streetViewPanorama;
    private StreetViewPanoramaFragment streetViewPanoramaFragment;
    private StreetViewPanorama.OnStreetViewPanoramaChangeListener streetViewPanoramaChangeListener = streetViewPanoramaLocation -> Log.e(TAG, "Street View Panorama Change Listener");
    private StreetViewPanorama.OnStreetViewPanoramaClickListener streetViewPanoramaClickListener = (orientation -> {
        Point point = streetViewPanorama.orientationToPoint(orientation);
        if (point != null) {
            streetViewPanorama.animateTo(
                    new StreetViewPanoramaCamera.Builder()
                            .orientation(orientation)
                            .zoom(streetViewPanorama.getPanoramaCamera().zoom)
                            .build(), PANORAMA_CAMERA_DURATION);
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        tex1 = findViewById(R.id.textView18);
        tex1.setVisibility(View.GONE);
        tex = findViewById(R.id.textView7);
        a = String.valueOf((Math.floor(Math.random() * 10)+1));
        countDownTimer1 = new CountDownTimer(4000,600) {
            @Override
            public void onTick(long millisUntilFinished) {
                tex.setText("Time left:");
                tex1.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Intent intent2 = new Intent(Vs.this, Place1.class);
                Intent intent = getIntent();
                String code = intent.getStringExtra("code");
                String id = intent.getStringExtra("id1");
                String id2 = intent.getStringExtra("id2");
                intent2.putExtra("id1",id);
                intent2.putExtra("id2", id2);
                intent2.putExtra("code", code);
                startActivity(intent2);
                finish();
            }
        };


        countDownTimer = new CountDownTimer(20000,1000) { // 2sec delay so 12 for 10 sec
            @Override
            public void onTick(long millisUntilFinished) {
                tex.setText("Time left:" + millisUntilFinished/1000 + " sec");
            }
            @Override
            public void onFinish() {
                tex1.setVisibility(View.VISIBLE);
                countDownTimer1.start();
            }
        };
        streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager()
                .findFragmentById(R.id.streetViewMap);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
        Bundle streetViewBundle = null;
        if (savedInstanceState != null)
            streetViewBundle = savedInstanceState.getBundle(STREET_VIEW_BUNDLE);
        streetViewPanoramaFragment.onCreate(streetViewBundle);
        countDownTimer.start();
    }
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        streetViewPanoramaFragment.onResume();
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Bundle mStreetViewBundle = outState.getBundle(STREET_VIEW_BUNDLE);
        if (mStreetViewBundle == null) {
            mStreetViewBundle = new Bundle();
            outState.putBundle(STREET_VIEW_BUNDLE, mStreetViewBundle);
        }
        streetViewPanoramaFragment.onSaveInstanceState(mStreetViewBundle);
    }
    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        this.streetViewPanorama = streetViewPanorama;
        if(num == 1)
        {
            ref.child("Taken").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    num++;
                }
                else
                {
                    b = num;
                    ref.child("Taken").child("1").setValue("1");
                }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            this.streetViewPanorama.setPosition(l1);
        }
         // now
        if(b == 1)
        {
            this.streetViewPanorama.setPosition(l1);
        }
        if(b == 2)
        {
            this.streetViewPanorama.setPosition(l2);
        }
        if(b == 3)
        {
            this.streetViewPanorama.setPosition(l3);
        }
        if(b == 4)
        {
            this.streetViewPanorama.setPosition(l4);
        }
        if(b == 5)
        {
            this.streetViewPanorama.setPosition(l5);
        }
        if(b == 6)
        {
            this.streetViewPanorama.setPosition(l6);
        }
        if(b == 7)
        {
            this.streetViewPanorama.setPosition(l7);
        }
        if(b == 8)
        {
            this.streetViewPanorama.setPosition(l8);
        }
        if(b == 9)
        {
            this.streetViewPanorama.setPosition(l9);
        }
        if(b == 10)
        {
            this.streetViewPanorama.setPosition(l10);
        }
        if(b == 11)
        {
            this.streetViewPanorama.setPosition(l11);
        }
        if(b == 12)
        {
            this.streetViewPanorama.setPosition(l12);
        }

































        this.streetViewPanorama.setOnStreetViewPanoramaChangeListener(streetViewPanoramaChangeListener);
        this.streetViewPanorama.setOnStreetViewPanoramaClickListener(streetViewPanoramaClickListener);
        this.streetViewPanorama.setStreetNamesEnabled(false);
    }
    @Override
    protected void onStop() {
        super.onStop();
        streetViewPanoramaFragment.onStop();
    }


}