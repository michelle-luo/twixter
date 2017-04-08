package com.example.ian.twixter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    Button sendButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        sendButton = (Button) findViewById(R.id.sendPost);
        cancelButton = (Button) findViewById(R.id.cancelPost);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textBox = (EditText) findViewById(R.id.editPost);
                String sms = textBox.getText().toString();

                try {
                    SmsManager smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage("40404", null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace(System.out);
                }
                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
