package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private EditText sign_mail, sign_pass, sign_user;
    private Button btnSign, btnclearMail, btnclearPass, btnclearUser;
    private TextView SignIn;
    private FirebaseAuth auth;
    private String rl, rn;
    private CheckBox checkBox;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = findViewById(R.id.imageView);
        auth = FirebaseAuth.getInstance();
        sign_mail = findViewById(R.id.sign_mail);
        sign_pass = findViewById(R.id.sign_pass);
        sign_user = findViewById(R.id.sign_username);
        SignIn = findViewById(R.id.sign_info);
        btnclearMail = findViewById(R.id.sign_clearMail);
        btnclearPass = findViewById(R.id.sign_clearPass);
        btnclearUser = findViewById(R.id.sign_clearUsername);
        checkBox = findViewById(R.id.show_pass2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    sign_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    sign_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
           }
        });
        btnclearUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_user.setText("");
            }
        });
        btnclearMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_mail.setText("");
            }
        });
        btnclearPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_pass.setText("");
            }
        });
        btnSign = findViewById(R.id.btnsignup);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = sign_user.getText().toString();
                String mail = sign_mail.getText().toString();
                String pass = sign_pass.getText().toString();
                rl = name.replaceAll(" ", "");
                rn = pass.replaceAll(" ", "");
                boolean flag = Patterns.EMAIL_ADDRESS.matcher(mail).matches();
                if(rl.isEmpty())
                {
                    if(!flag)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please Enter a username!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if (mail.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter a mail!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (rn.isEmpty()) {
                            if(!flag)
                            {
                                Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Please Enter a password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            if(!flag)
                            {
                                Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if ((rl.length() > 2 && rl.length() < 9) && rl.matches(".*[a-z].*") ) // בודק אם השם מכיל a-z וגם גדול מ 2 וקטן מ 9
                                {
                                    if(rn.length() > 13)
                                    {
                                        Toast.makeText(getApplicationContext(), "Password too long!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                       try {
                                           DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                           ref.child("Names").child(rl).addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                   if(snapshot.exists()){
                                                       Toast.makeText(getApplicationContext(),"Username is taken!", Toast.LENGTH_SHORT).show();
                                                   }
                                                   else
                                                   {
                                                       auth.createUserWithEmailAndPassword(mail,rn).addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                                                           @Override
                                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                                               if(!task.isSuccessful())
                                                               {
                                                                   if (rn.length() < 6)
                                                                       Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long!", Toast.LENGTH_SHORT).show();
                                                                   else {
                                                                       if (mail.contains("@gmail.com"))
                                                                           Toast.makeText(getApplicationContext(), "Email already used!", Toast.LENGTH_SHORT).show();
                                                                       else
                                                                           Toast.makeText(getApplicationContext(), "Enter a valid mail!", Toast.LENGTH_SHORT).show();
                                                                   }
                                                               }
                                                               else
                                                               {
                                                                   FirebaseDatabase Node = FirebaseDatabase.getInstance();
                                                                   DatabaseReference reference = Node.getReference("Users");
                                                                   Users add = new Users(mail, pass, 0, 0, rl);
                                                                   reference.child(auth.getCurrentUser().getUid()).setValue(add);
                                                                   DatabaseReference ref = Node.getReference("Names");
                                                                   ref.child(rl).setValue(rl);
                                                                   DatabaseReference g = FirebaseDatabase.getInstance().getReference("fif").child(auth.getCurrentUser().getUid());
                                                                   g.setValue(0);
                                                                   startActivity(new Intent(HomeActivity.this, Homescreen.class));
                                                                   Toast.makeText(getApplicationContext(), "The account was successfully created!", Toast.LENGTH_SHORT).show();
                                                                   finish();
                                                               }
                                                           }
                                                       });
                                                   }
                                               }
                                               @Override
                                               public void onCancelled(@NonNull DatabaseError error) {
                                               }
                                           });
                                       }
                                       catch (Exception e)
                                       {
                                           Toast.makeText(getApplicationContext(), "Invalid username!", Toast.LENGTH_SHORT).show();
                                       }
                                    }
                                }
                                else
                                {
                                    if (!rl.matches(".*[a-z].*"))
                                    {
                                        Toast.makeText(getApplicationContext(), "Invalid username!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Username must be between 3-8 char long!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
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
            Intent intent1 = new Intent(HomeActivity.this,odotcoder.class);
            intent1.putExtra("keys","signup");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(HomeActivity.this,odotproject.class);
            intent1.putExtra("keys","signup");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
