package com.example.ian.twixter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class ReceiveText extends BroadcastReceiver {

    public ReceiveText() {

    }

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs;
        String str = "";

        if (bundle == null) {
            return;
        }
        // get message format from bundle
        String format = bundle.getString("format");
        // retrieve the received messages from bundle
        Object[] pdus;
        pdus = (Object[]) bundle.get("pdus");
        if (pdus == null) {
            return;
        }
        msgs = new SmsMessage[pdus.length];

        // for every message received
        for (int i = 0; i < msgs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i], format);
            }
            if (msgs[i].getOriginatingAddress() == "+16172497980") {
                str += msgs[i].getOriginatingAddress() + " : " +
                        msgs[i].getMessageBody();
            }
        }
        Log.d("ReceiveText", str);
    }

}
