package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class Waiting extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageView imageView;
    private Button cancel;
    private TextView code, change;
    private String room_code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        cancel = findViewById(R.id.button90);
        progressBar = findViewById(R.id.progressbar);
        code = findViewById(R.id.room_code);
        imageView = findViewById(R.id.ve);
        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        change = findViewById(R.id.textView20);
        room_code = generateString(6);
        code.setText("Your code:" + room_code);
        FirebaseDatabase Node = FirebaseDatabase.getInstance();
        DatabaseReference reference = Node.getReference("Rooms");
        reference.child(room_code).setValue(room_code);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.exists()))
                {
                    Toast.makeText(getApplicationContext(), "Someone joined!", Toast.LENGTH_LONG).show();

                    //imageView.setVisibility(View.VISIBLE);  // found!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                 //progressBar.setVisibility(View.GONE);  // set text found  // MOVE TO OTHER ACTIVITY + FOUND!
                 //change.setText("Found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Can't read the data!", Toast.LENGTH_LONG).show();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Rooms").child(room_code);
                ref.removeValue();
                startActivity(new Intent(Waiting.this, Homescreen.class));
                finish();
            }
        });





    }
    @Override
    public void onBackPressed() {

    }
    private String generateString(int length){
        char[] chars ="QWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for ( int i= 0; i<length;i++)
        {
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);

        }
        return stringBuilder.toString();
    }
}