package com.example.ian.twixter;

import android.telephony.SmsManager;

public class sendText {
    protected SmsManager smsMgr;
    protected String number = "2015606871";

    protected String SMS_SENT = "SMS_SENT";
    protected String SMS_DELIVERED = "SMS_DELIVERED";

    public sendText(String message) {
        smsMgr = SmsManager.getDefault();
        smsMgr.sendTextMessage(number, null, message, null, null);
    }
}
