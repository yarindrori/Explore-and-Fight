package com.example.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rec extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();
        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Taken");
        r.child(id).child("busy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    try
                    {
                        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
                        {
                            Bundle bundle = intent.getExtras();
                            if (bundle != null)
                            {
                                String num = "";
                                String msg = "";
                                Object[] pdusObj = (Object[]) bundle.get("pdus");
                                final SmsMessage[] messages = new SmsMessage[pdusObj.length];
                                for (int i = 0; i < pdusObj.length; i++) {
                                    if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
                                    {
                                        String format = bundle.getString("format");
                                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                                    }
                                    else
                                    {
                                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                                    }
                                    num = messages[i].getOriginatingAddress();
                                    msg = messages[i].getMessageBody();
                                }
                                Toast.makeText(context, "New SMS from: " +num + "-" + "\n"+msg, Toast.LENGTH_LONG).show();
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(num, null, "Please do not disturb! I'm playing a game!", null, null);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
