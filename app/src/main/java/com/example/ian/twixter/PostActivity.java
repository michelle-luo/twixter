package com.example.ian.twixter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {
    Button sendButton, cancelButton, helpPost;
    TextView charCounter;
    Intent intent;
    EditText tweetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        sendButton = (Button) findViewById(R.id.sendPost);
        cancelButton = (Button) findViewById(R.id.cancelPost);
        helpPost = (Button) findViewById(R.id.helpPost);
        charCounter = (TextView) findViewById(R.id.charCount);
        tweetText = (EditText) findViewById(R.id.editPost);

        intent = getIntent();

        tweetText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String newCount = charSequence.toString().length() + "/140";
                charCounter.setText(newCount);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editPost = (EditText) findViewById(R.id.editPost);
                String message = editPost.getText().toString();

                /* check message length */
                if (message.length() == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a Tweet.",
                            Toast.LENGTH_LONG).show();
                }
                else if (message.length() > 140) {
                    Toast.makeText(getBaseContext(), "You are " +
                                    Integer.toString(message.length() - 140) +
                                    " characters over the 140 character limit, please try again!",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    /* send message */
                    SendText.sendText(getBaseContext(), "40404", message);
                    intent.putExtra("numSms", 1);
                    setResult(Activity.RESULT_OK, intent);

                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    };
                    Handler h = new Handler();
                    h.postDelayed(r, 1000);
                }

            }
        });

        helpPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(PostActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("Send a tweet and share your thoughts with the world! " +
                        "Type the message that you want to share in the box, then hit send.");
                helpDialog.setCancelable(true);

                helpDialog.setPositiveButton(
                        "Ok, I get it",
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
