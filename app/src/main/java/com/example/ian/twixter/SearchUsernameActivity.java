package com.example.ian.twixter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.Objects;

public class SearchUsernameActivity extends AppCompatActivity {
    SearchView searchUserText;
    EditText numTextBox;
    Button searchUserButton, helpSearchUser, searchUserCancel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_username);

        searchUserText = (SearchView) findViewById(R.id.searchUserText);
        numTextBox = (EditText) findViewById(R.id.numTextBox);
        searchUserButton = (Button) findViewById(R.id.searchUserButton);
        searchUserCancel = (Button) findViewById(R.id.searchUserCancel);
        helpSearchUser = (Button) findViewById(R.id.helpSearchUser);

        searchUserText.setQueryHint("Search for a user's tweets");
        numTextBox.setHint("How many tweets?");

        searchUserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = searchUserText.getQuery().toString();
                username = username.replace("/[^\\w]/", "");
                if (username == null || username.length() == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a username to search",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                String toNum = numTextBox.getText().toString();

                if (Objects.equals(toNum, "")) {
                    Toast.makeText(getBaseContext(), "Please choose a number of tweets to receive",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int numTexts = Integer.parseInt(toNum);
                if (numTexts > 10) {
                    numTexts = 10;
                }
                String message = "su " + numTexts + " " + username;
                SendText.sendText(getBaseContext(), "+17312567648", message);

                intent = new Intent();
                // one text for sending query
                intent.putExtra("numSms", 1);
                setResult(Activity.RESULT_OK, intent);

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        setResult(RESULT_OK, intent);
                        Intent intent = new Intent(SearchUsernameActivity.this, FeedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 3000);
            }
        });

        helpSearchUser.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(SearchUsernameActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("Search for a specific username here to see tweets that" +
                        "pertain to them. Type the username in the first box and input the " +
                        "number of tweets you would like to see in the second box.");
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
                                Intent intent = new Intent(SearchUsernameActivity.this, HelpActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = helpDialog.create();
                alert.show();
            }
        });

        searchUserCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
