package com.example.ian.twixter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


/**
 * Created by Adrian on 4/27/2017.
 */

public class TrendingActivity extends AppCompatActivity{
    Button searchButton, cancelButton, helpSearch;
    //SearchView search;
    EditText numTexts;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trending_topics);

        searchButton = (Button) findViewById(R.id.searchTrendingButton);
        cancelButton = (Button) findViewById(R.id.cancelTrendingButton);
        helpSearch = (Button) findViewById(R.id.helpTrendingSearch);
        numTexts = (EditText) findViewById(R.id.numTextBox);
        numTexts.setHint("How many tweets?");

        intent = getIntent();

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent.putExtra("numSms", 1);
                setResult(Activity.RESULT_OK, intent);

                String toNum = numTexts.getText().toString();

                if (Objects.equals(toNum, "")) {
                    Toast.makeText(getBaseContext(), "Please choose a number of tweets to receive",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int numberOfTexts = Integer.parseInt(numTexts.getText().toString());
                if (numberOfTexts == 0) {
                    Toast.makeText(getBaseContext(), "Please choose a number of trending topics to receive",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (numberOfTexts > 10) {
                    numberOfTexts = 10;
                }


                String msg = "tt " + numberOfTexts;

                /* send message */
                SendText.sendText(getBaseContext(), "+17312567648", msg);

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(TrendingActivity.this, FeedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                };
                Handler h = new Handler();
                h.postDelayed(r, 3000);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        helpSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(TrendingActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("To see the most popular tweets on Twitter, " +
                        "simply enter the amount of trending tweets you would like to receive " +
                        "in the text box. Then hit send, and " +
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
                                Intent intent = new Intent(TrendingActivity.this, HelpActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = helpDialog.create();
                alert.show();
            }
        });
    }


}

