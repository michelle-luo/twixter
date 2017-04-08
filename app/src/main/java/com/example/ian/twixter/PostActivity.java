package com.example.ian.twixter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
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
                EditText editPost = (EditText) findViewById(R.id.editPost);
                String number = "40404";
                String message = editPost.getText().toString();

                if (message.length() > 0) {
                    sendMsg(message, number);
                }
                else {
                    Toast.makeText(getBaseContext(), "Please enter a Tweet.",
                            Toast.LENGTH_LONG).show();
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

    private void sendMsg(String message, String number) {
        SmsManager smsMgr;
        try {
            smsMgr = SmsManager.getDefault();
            smsMgr.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Tweet sent!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Tweet failed to send, please try again!",
                    Toast.LENGTH_LONG).show();
            Log.e("Twixter", "send message", e);
        }
    }
}
