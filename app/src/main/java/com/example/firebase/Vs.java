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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.Stack;

public class Vs extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private LatLng l1 = new LatLng(13.41241963977408,103.8681012543761); // Cambodia
    private LatLng l2 = new LatLng(-33.85803087516396,151.21450292817096); // Sydney
    private LatLng l3 = new LatLng(48.86069185564439,2.2908392866548306); // Paris
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
    private String score = "";
    private String tot = "";
    private int b = 0;
    private Boolean papa = true;
    private TextView tex,tex1;
    private String a;
    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimer1;
    private Boolean f = false;
    private DatabaseReference reference100 = FirebaseDatabase.getInstance().getReference("Match");
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
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        String id = intent.getStringExtra("id1");
        String id2 = intent.getStringExtra("id2");
        int num2 = intent.getIntExtra("ran2",0);
        tex1 = findViewById(R.id.textView18);
        tex1.setVisibility(View.GONE);
        tex = findViewById(R.id.textView7);
        a = String.valueOf((Math.floor(Math.random() * 10)+1));
        countDownTimer1 = new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tex.setText("Time left:");
                tex1.setText("  "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Intent intent2 = new Intent(Vs.this, Place1.class);
                intent2.putExtra("id1",id);
                intent2.putExtra("id2", id2);
                intent2.putExtra("code", code);
                intent2.putExtra("ran2",num2);
                startActivity(intent2);
                finish();
            }
        };


        countDownTimer = new CountDownTimer(22000,1000) { // 2sec delay so 12 for 10 sec
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
        Intent intent = getIntent();
        int top = intent.getIntExtra("ran",0);
        if(top == 1)
        {
            this.streetViewPanorama.setPosition(l1);
        }
        if(top == 2)
        {
            this.streetViewPanorama.setPosition(l2);
        }
        if(top == 3)
        {
            this.streetViewPanorama.setPosition(l3);
        }
        if(top == 4)
        {
            this.streetViewPanorama.setPosition(l4);
        }
        if(top == 5)
        {
            this.streetViewPanorama.setPosition(l5);
        }
        if(top == 6)
        {
            this.streetViewPanorama.setPosition(l6);
        }
        if(top == 7)
        {
            this.streetViewPanorama.setPosition(l7);
        }
        if(top == 8)
        {
            this.streetViewPanorama.setPosition(l8);
        }
        if(top == 9)
        {
            this.streetViewPanorama.setPosition(l9);
        }
        if(top == 10)
        {
            this.streetViewPanorama.setPosition(l10);
        }
        if(top == 11)
        {
            this.streetViewPanorama.setPosition(l11);
        }
        if(top == 12)
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