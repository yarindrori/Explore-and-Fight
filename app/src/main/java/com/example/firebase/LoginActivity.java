package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
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

public class LoginActivity extends AppCompatActivity {
    private TextView reg;
    private EditText login_mail, login_pass;
    private Button btnsignin, btnclearMail, btnclearPass;
    private FirebaseAuth auth2;
    private FirebaseAuth.AuthStateListener stateListener;
    private CheckBox checkBox;
    private ImageView imageView;
    private Boolean f = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView = findViewById(R.id.imageView3);
        auth2 = FirebaseAuth.getInstance();
        login_mail = findViewById(R.id.login_mail);
        login_pass = findViewById(R.id.login_pass);
        btnsignin = findViewById(R.id.btnSignIn);
        btnclearMail = findViewById(R.id.login_clearMail);
        btnclearPass = findViewById(R.id.login_clearPass);
        checkBox = findViewById(R.id.show_pass);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    login_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    login_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btnclearMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_mail.setText("");
            }
        });
        btnclearPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_pass.setText("");
            }
        });
        reg = findViewById(R.id.login_info);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }
        });
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth2.getCurrentUser();
                if(user != null)
                {
                    Toast.makeText(getApplicationContext(),"You're logged in!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, Homescreen.class));
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please login!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = login_mail.getText().toString();
                String pass = login_pass.getText().toString();
                boolean flag = Patterns.EMAIL_ADDRESS.matcher(mail).matches();
                if(mail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter a mail!", Toast.LENGTH_SHORT).show();
                }
                else if (pass.isEmpty())
                {
                    if(flag == false)
                    {
                        Toast.makeText(getApplicationContext(), "Invaild email!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please Enter a password!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (!(mail.isEmpty() && pass.isEmpty())) {
                    if (flag == false) {
                        Toast.makeText(getApplicationContext(), "Invaild email!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        auth2.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    login_mail.setText("");
                                    login_pass.setText("");
                                    Toast.makeText(getApplicationContext(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, Homescreen.class));
                                    finish();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {

    }


}