package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Stack;

public class Leaderboards extends AppCompatActivity {
    private TextView tex1, tex2 , tex3 , tex4 , tex5 , tex6 , tex7 , tex8 , tex9 , tex10 ,tex11,tex12,tex13, tex14;
    private TextView y1,y2,y3,y4,y5;
    private ImageView goback;
    private FirebaseAuth auth;
    private Stack<String> s = new Stack<String>();
    private Stack<Integer> s2 = new Stack<Integer>();
    private Stack<Users> usersStack = new Stack<Users>();
    private Boolean f = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();
        // top
        tex1 = findViewById(R.id.player14);// name1
        tex2 = findViewById(R.id.player15); // name 2
        tex3 = findViewById(R.id.player16); // name 3
        tex4 = findViewById(R.id.player17); // name 4
        tex5 = findViewById(R.id.player27); // name 5
        // points
        tex6 = findViewById(R.id.player20); // 1
        tex7 = findViewById(R.id.player21); //2
        tex8 = findViewById(R.id.player22); //3
        tex9 = findViewById(R.id.player23); // 4
        tex10 = findViewById(R.id.player28); // 5
        // you info
        tex11 = findViewById(R.id.player19); // you not showing up
        tex12 = findViewById(R.id.player18); // name
        tex13 = findViewById(R.id.player13); // place
        tex14 = findViewById(R.id.player32); // score
        // you location
        y1 = findViewById(R.id.player31); // you 1
        y2 = findViewById(R.id.player30); // you 2
        y3 = findViewById(R.id.player29);// you 3
        y4 = findViewById(R.id.player25);// you 4
        y5 = findViewById(R.id.player8);// you 5
        goback = findViewById(R.id.imageView15);
        // להסתיר את מה שלא צריך
        y1.setVisibility(View.GONE);
        y2.setVisibility(View.GONE);
        y3.setVisibility(View.GONE);
        y4.setVisibility(View.GONE);
        y5.setVisibility(View.GONE);
        tex11.setVisibility(View.GONE);
        tex12.setVisibility(View.GONE);
        tex13.setVisibility(View.GONE);
        tex14.setVisibility(View.GONE);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Leaderboards.this, Homescreen.class));
                finish();
            }
        });
        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users");
        Query q = r.orderByChild("points");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot post: snapshot.getChildren())
               {
                   // {password=math2323, access=false, mail=e@gmail.com, coins=0, points=22, username=yarin} דאטה
                   String name = post.getValue().toString();
                    if (!name.equals("0"))
                    {
                        name = name.substring(name.indexOf("me=")+3);
                        name = name.substring(0, name.indexOf("}"));
                        s.push(name);
                        Log.d("name","value is "+ name);
                    }
                    String p = post.getValue().toString();
                    if (!p.equals("0"))
                    {
                        p = p.substring(p.indexOf("ts=")+3);
                        p = p.substring(0,p.indexOf(","));
                        Integer points = Integer.parseInt(p);
                        s2.push(points);
                        Log.d("points","points "+ points);
                    }
                   String pass = post.getValue().toString();
                   if (!pass.equals("0"))
                   {
                       pass = pass.substring(pass.indexOf("rd=")+3);
                       pass = pass.substring(0, pass.indexOf(","));
                   }
                   String mail = post.getValue().toString();
                   if (!mail.equals("0"))
                   {
                       mail = mail.substring(mail.indexOf("il=")+3);
                       mail = mail.substring(0, mail.indexOf(","));
                   }
                   String coins = post.getValue().toString();
                   if (!coins.equals("0"))
                   {
                       coins = coins.substring(coins.indexOf("ns=")+3);
                       coins = coins.substring(0, coins.indexOf(","));
                   }
                   String access = post.getValue().toString();
                   if (!access.equals("0"))
                   {
                       access = access.substring(access.indexOf("ss=")+3);
                       access = access.substring(0,access.indexOf(","));
                       if (access.equals("true"))
                       {
                           Vipuser vipuser = new Vipuser(mail,pass,Integer.parseInt(coins),Integer.parseInt(p),name,true);
                           usersStack.push(vipuser);
                       }
                       else
                       {
                           Users user = new Users(mail,pass,Integer.parseInt(coins),Integer.parseInt(p),name);
                           usersStack.push(user);
                       }
                   }
                }

               DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(id);
               ref.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if (snapshot.exists() && !f)
                       {
                           f = true;
                           String y = snapshot.child("username").getValue().toString(); // שם של מי שצריך לבדוק
                           if(!s.isEmpty() && !s2.isEmpty()&& !usersStack.isEmpty())
                           {
                               if(s.peek().equals(y))
                               {
                                   y1.setVisibility(View.VISIBLE);
                               }
                               if (usersStack.peek() instanceof Vipuser)
                               {
                                   tex1.setTextColor(getResources().getColor(R.color.gold)); // VIP
                               }
                               usersStack.pop();
                               tex1.setText(s.pop());
                               tex6.setText("←" + String.valueOf(s2.pop()));
                           }
                           else
                           {
                               tex1.setText("Empty!");
                           }
                           if(!s.isEmpty() && !s2.isEmpty()  && !usersStack.isEmpty())
                           {
                               if (s.peek().equals(y)) // 2
                               {
                                   y2.setVisibility(View.VISIBLE);
                               }
                               if (usersStack.peek() instanceof Vipuser)
                               {
                                   tex2.setTextColor(getResources().getColor(R.color.gold)); // VIP
                               }
                               usersStack.pop();
                               tex2.setText(s.pop());
                               tex7.setText("←" +String.valueOf(s2.pop()));
                           }
                           else
                           {
                               tex2.setText("Empty!");
                           }
                           if(!s.isEmpty()&& !s2.isEmpty() && !usersStack.isEmpty())
                           {
                               if (s.peek().equals(y)) // 3
                               {
                                   y3.setVisibility(View.VISIBLE);
                               }
                               if (usersStack.peek() instanceof Vipuser)
                               {
                                   tex3.setTextColor(getResources().getColor(R.color.gold)); // VIP
                               }
                               usersStack.pop();
                               tex3.setText(s.pop());
                               tex8.setText("←" +String.valueOf(s2.pop()));
                           }
                           else
                           {
                               tex3.setText("Empty!");
                           }
                           if(!s.isEmpty()&& !s2.isEmpty()&& !usersStack.isEmpty())
                           {
                               if (s.peek().equals(y)) // 4
                               {
                                   y4.setVisibility(View.VISIBLE);
                               }
                               if (usersStack.peek() instanceof Vipuser)
                               {
                                   tex4.setTextColor(getResources().getColor(R.color.gold)); // VIP
                               }
                               usersStack.pop();
                               tex4.setText(s.pop());
                               tex9.setText("←" +String.valueOf(s2.pop()));
                           }
                           else
                           {
                               tex4.setText("Empty!");
                           }
                           if(!s.isEmpty()&& !s2.isEmpty()&& !usersStack.isEmpty())
                           {
                               if (s.peek().equals(y)) // 5
                               {
                                   y5.setVisibility(View.VISIBLE);
                               }
                               if (usersStack.peek() instanceof Vipuser)
                               {
                                   tex5.setTextColor(getResources().getColor(R.color.gold)); // VIP
                               }
                               usersStack.pop();
                               tex5.setText(s.pop());
                               tex10.setText("←" +String.valueOf(s2.pop()));
                           }
                           else
                           {
                               tex5.setText("Empty!");
                           }
                           int placement = 6; // placement
                           while (!s.isEmpty() && !s2.isEmpty()&& !usersStack.isEmpty())
                           {
                               if(s.peek().equals(y))
                               {
                                   tex11.setVisibility(View.VISIBLE);
                                   tex13.setVisibility(View.VISIBLE);
                                   tex12.setVisibility(View.VISIBLE);
                                   tex14.setVisibility(View.VISIBLE);
                                   if (usersStack.peek() instanceof Vipuser)
                                   {
                                       tex12.setTextColor(getResources().getColor(R.color.gold)); // VIP
                                   }
                                   usersStack.pop();
                                   tex12.setText(s.pop());
                                   tex13.setText(String.valueOf(placement)+".");
                                   tex14.setText("←" +String.valueOf(s2.pop()));


                               }
                               else
                               {
                                   usersStack.pop();
                                   placement++;
                                   s.pop();
                                   s2.pop();
                               }
                           }

                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                   }
               });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
            Intent intent1 = new Intent(Leaderboards.this,odotcoder.class);
            intent1.putExtra("keys","leader");
            startActivity(intent1);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.odot2)
        {
            Intent intent1 = new Intent(Leaderboards.this,odotproject.class);
            intent1.putExtra("keys","leader");
            startActivity(intent1);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
