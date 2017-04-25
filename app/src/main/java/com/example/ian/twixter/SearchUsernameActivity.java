package com.example.ian.twixter;

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

public class SearchUsernameActivity extends AppCompatActivity {
    SearchView searchUserText;
    EditText numTextBox;
    Button searchUserButton, helpSearchUser;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_username);

        searchUserText = (SearchView) findViewById(R.id.searchUserText);
        searchUserButton = (Button) findViewById(R.id.searchUserButton);
        searchUserText.setQueryHint("Search for a user's tweets");
        helpSearchUser = (Button) findViewById(R.id.helpSearchUser);
        numTextBox = (EditText) findViewById(R.id.numTextBox);
        numTextBox.setHint("How many tweets?");

        intent = new Intent();

        searchUserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = searchUserText.getQuery().toString();
                int numTexts = Integer.parseInt(numTextBox.getText().toString());

                String message = "su " + numTexts + " " + username;
                SendText.sendText(getBaseContext(), "+17312567648", message);
                // one text for query, more for search results
                intent.putExtra("numSms", 1 + numTexts);
                setResult(RESULT_OK, intent);

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 1000);
            }
        });

        helpSearchUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(SearchUsernameActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("This is the help section for Search by Username Page");
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
    }
}
