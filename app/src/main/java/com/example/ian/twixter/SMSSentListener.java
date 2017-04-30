package com.example.ian.twixter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

class SMSSentListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int resultCode = this.getResultCode();
        boolean sent = (resultCode == Activity.RESULT_OK);
        Toast.makeText(context, "Request sent!", Toast.LENGTH_LONG).show();
        context.unregisterReceiver(this);
    }
}
