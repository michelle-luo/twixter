package com.example.ian.twixter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {
    Button sendButton, cancelButton;
    SendText sendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        sendButton = (Button) findViewById(R.id.sendPost);
        cancelButton = (Button) findViewById(R.id.cancelPost);

        sendSMS = getIntent().getExtras().getParcelable("sendSMS");

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editPost = (EditText) findViewById(R.id.editPost);
                String message = editPost.getText().toString();

                /* check message length */
                if (message.length() == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a Tweet.",
                            Toast.LENGTH_LONG).show();
                }
                if (message.length() > 160) {
                    Toast.makeText(getBaseContext(), "You are " +
                                    Integer.toString(message.length() - 160) +
                                    " characters over the 160 character limit, please try again!",
                            Toast.LENGTH_LONG).show();
                }

                /* send message */
                sendSMS = sendSMS.sendText(getBaseContext(), "40404", message);

                /* hide keyboard after click */
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
