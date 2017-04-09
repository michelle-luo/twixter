package com.example.ian.twixter;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

class SendText {
    static void sendText(Context context, String number, String message) {
        SmsManager smsMgr;
        try {
            smsMgr = SmsManager.getDefault();
            smsMgr.sendTextMessage(number, null, message, null, null);
            Toast.makeText(context, "Tweet sent!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(context, "Tweet failed to send: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
