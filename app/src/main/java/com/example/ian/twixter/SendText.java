package com.example.ian.twixter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.Toast;

abstract class SendText {
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

            /* update preferences */
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();

            /* get num texts already there */
            String textsSentKey = "com.example.ian.twixter.texts";
            int textsSent = prefs.getInt(textsSentKey, -1);
            if (textsSent != -1) {
                textsSent++;
            }
            else {
                textsSent = 0;
            }

            /* store back in shared prefs */
            editor.putInt(textsSentKey, textsSent);
            editor.commit();
        }
        catch (Exception e) {
            Toast.makeText(context, "Failed to send: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
