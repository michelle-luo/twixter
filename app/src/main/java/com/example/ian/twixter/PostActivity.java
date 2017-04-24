package com.example.ian.twixter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {
    Button sendButton, cancelButton, helpPost;
    SendText sendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        sendButton = (Button) findViewById(R.id.sendPost);
        cancelButton = (Button) findViewById(R.id.cancelPost);
        helpPost = (Button) findViewById(R.id.helpPost);

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
                if (message.length() > 140) {
                    Toast.makeText(getBaseContext(), "You are " +
                                    Integer.toString(message.length() - 140) +
                                    " characters over the 140 character limit, please try again!",
                            Toast.LENGTH_LONG).show();
                }

                /* send message */
                sendSMS = sendSMS.sendText(getBaseContext(), "40404", message);

                /* hide keyboard after click */
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        // if you are redirecting from a fragment then use getActivity() as the context.
                        startActivity(new Intent(PostActivity.this, MainActivity.class));
                        finish();
                    }
                };

                Handler h = new Handler();
                // The Runnable will be executed after the given delay time
                h.postDelayed(r, 1000); // will be delayed for 1 second

            }
        });

        helpPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(PostActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("This is the help section for Post Page");
                helpDialog.setCancelable(true);

                helpDialog.setPositiveButton(
                        "Ok get it",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                helpDialog.setNegativeButton(
                        "Still confused",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent intent = new Intent(PostActivity.this, HelpActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = helpDialog.create();
                alert.show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
