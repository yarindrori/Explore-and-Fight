package com.example.firebase;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Vs extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
        private TextView tex;
        private String a;
        private CountDownTimer countDownTimer;
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
        tex = findViewById(R.id.textView7);
        a = String.valueOf((Math.floor(Math.random() * 10)+1));
        countDownTimer = new CountDownTimer(7000,1000) { // 2sec delay so 12 for 10 sec
            @Override
            public void onTick(long millisUntilFinished) {
                tex.setText("Time left:" + millisUntilFinished/1000 + " sec");
            }

            @Override
            public void onFinish() {
                Intent intent2 = new Intent(Vs.this, Place1.class);
                Intent intent = getIntent();
                String id = intent.getStringExtra("id1");
                String id2 = intent.getStringExtra("id2");
                intent2.putExtra("id1",id);
                intent2.putExtra("id2", id2);
                startActivity(intent2);
                finish();
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
        this.streetViewPanorama.setPosition(new LatLng(32.073420488301636, 34.79257760610014));
        this.streetViewPanorama.setOnStreetViewPanoramaChangeListener(streetViewPanoramaChangeListener);
        this.streetViewPanorama.setOnStreetViewPanoramaClickListener(streetViewPanoramaClickListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        streetViewPanoramaFragment.onStop();
    }


    }
