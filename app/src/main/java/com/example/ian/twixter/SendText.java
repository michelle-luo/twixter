package com.example.ian.twixter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

class SendText {
    static void sendText(Context context, String number, String message) {
        SmsManager smsMgr;
        String SENT_SMS_FLAG = "SENT_SMS_FLAG";
        try {
            smsMgr = SmsManager.getDefault();
            Intent intent = new Intent(SENT_SMS_FLAG);
            PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0,
                    intent, 0);
            context.registerReceiver(new SMSSentListener(), new IntentFilter(SENT_SMS_FLAG));
            smsMgr.sendTextMessage(number, null, message, sentIntent, null);
        }
        catch (Exception e) {
            Toast.makeText(context, "Tweet failed to send: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
