package com.example.ian.twixter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.widget.Toast;

class SendText implements Parcelable {
    private int numSMS;

    SendText() {
        numSMS = 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(numSMS);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SendText createFromParcel(Parcel parcel) {
            return new SendText(parcel);
        }

        public SendText[] newArray(int size) {
            return new SendText[size];
        }
    };

    private SendText(Parcel in) {
        numSMS = in.readInt();
    }

    SendText sendText(Context context, String number, String message) {
        SmsManager smsMgr;
        String SENT_SMS_FLAG = "SENT_SMS_FLAG";
        try {
            smsMgr = SmsManager.getDefault();
            Intent intent = new Intent(SENT_SMS_FLAG);
            PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0,
                    intent, 0);
            context.registerReceiver(new SMSSentListener(), new IntentFilter(SENT_SMS_FLAG));
            smsMgr.sendTextMessage(number, null, message, sentIntent, null);
            numSMS++;
        }
        catch (Exception e) {
            Toast.makeText(context, "Tweet failed to send: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return this;
    }

    public int getSMSCount() {
        return numSMS;
    }
}