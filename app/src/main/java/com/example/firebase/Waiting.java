package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Waiting extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageView imageView;
    private Button cancel;
    private TextView code, change;
    private String room_code = "";
    private Boolean flag = true;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private String id2;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String id = user.getUid();
        cancel = findViewById(R.id.button90);
        progressBar = findViewById(R.id.progressbar);
        code = findViewById(R.id.room_code);
        imageView = findViewById(R.id.ve);
        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        change = findViewById(R.id.textView20);
        room_code = generateString(5);
        code.setText(" Your code:" + room_code);
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Games").child(room_code);
        reference1.setValue(room_code);
        FirebaseDatabase Node = FirebaseDatabase.getInstance();
        DatabaseReference rik = Node.getReference("temp");
        rik.child(room_code).setValue(id);
        DatabaseReference reference = Node.getReference("Rooms");
        reference.child(room_code).setValue(room_code);
        reference.child(room_code).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                }
                else
                {
                    if(flag == true)
                    {
                        change.setText("      Player found!");
                        imageView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        DatabaseReference refe = FirebaseDatabase.getInstance().getReference("waitroom").child(room_code);
                        refe.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists())
                                {
                                    id2 = snapshot.getValue().toString();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Random rn = new Random();
                        int num = rn.nextInt(12) + 1;
                        DatabaseReference rop = FirebaseDatabase.getInstance().getReference("Match").child(room_code);
                        rop.setValue(num);
                        int num2 = rn.nextInt(12)+1;
                        while (num2 == num) // אם יש אותו מספר
                        {
                            num2 = rn.nextInt(12)+1;
                        }
                        DatabaseReference rop2 = FirebaseDatabase.getInstance().getReference("Match").child(room_code+"1");
                        rop2.setValue(num2);
                        int num3 = num2;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DatabaseReference reft = FirebaseDatabase.getInstance().getReference("waitroom").child(room_code);
                                reft.removeValue();
                                DatabaseReference reff = FirebaseDatabase.getInstance().getReference("temp").child(room_code);
                                reff.removeValue();
                                Intent intent = new Intent(Waiting.this, Vs.class);
                                intent.putExtra("code", room_code);
                                intent.putExtra("id1", id);
                                intent.putExtra("id2", id2);
                                intent.putExtra("ran", num);
                                intent.putExtra("ran2", num3);
                                startActivity(intent);
                                finish();
                            }
                        },850);
                    }

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
                flag = false;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Games").child(room_code);
                reference.removeValue();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Rooms").child(room_code);
                ref.removeValue();
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("temp").child(room_code);
                reference1.removeValue();
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
            Intent intent1 = new Intent(Waiting.this,odotcoder.class);
            intent1.putExtra("keys","create");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(Waiting.this,odotproject.class);
            intent1.putExtra("keys","create");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}