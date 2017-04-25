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

public class SearchHashtagActivity extends AppCompatActivity {
    Button searchHashButton, cancelHashButton, helpSearchHash;
    SearchView search;
    EditText numTexts;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hashtag);

        searchHashButton = (Button) findViewById(R.id.searchHashButton);
        cancelHashButton = (Button) findViewById(R.id.cancelHashButton);
        helpSearchHash = (Button) findViewById(R.id.helpSearchHash);
        search = (SearchView) findViewById(R.id.searchUserText);
        search.setQueryHint("Search for a topic here");
        numTexts = (EditText) findViewById(R.id.numTextBox);
        numTexts.setHint("How many tweets?");

        intent = getIntent();

        searchHashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent.putExtra("numSms", 1);
                setResult(Activity.RESULT_OK, intent);

                String parameter = search.getQuery().toString();
                parameter = parameter.replaceAll("/[^\\w]/", "");
                int numberOfTexts = Integer.parseInt(numTexts.getText().toString());
                if (numberOfTexts > 10) {
                    numberOfTexts = 10;
                }

                String msg = "sh " + numberOfTexts + " " + parameter;

                /* send message */
                SendText.sendText(getBaseContext(), "+17312567648", msg);

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

        cancelHashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        helpSearchHash.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(SearchHashtagActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("To see what people are saying about a specific hashtag, " +
                        "type the hashtag you want to search in the first box. In the second " +
                        "box, input the number of tweets you want to see. Then hit send, and " +
                        "check out your feed.");
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
                                Intent intent = new Intent(SearchHashtagActivity.this, HelpActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = helpDialog.create();
                alert.show();
            }
        });
    }

}
