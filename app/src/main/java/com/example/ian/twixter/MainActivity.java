package com.example.ian.twixter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView header;
    String headerText;
    Button feedButton, postButton, searchButton, dmButton, helpButton;
    int numSms;

    protected void updateHeader() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        /* get num texts already there */
        String textsSentKey = "com.example.ian.twixter.texts";
        int textsSent = prefs.getInt(textsSentKey, -1);
        if (textsSent == -1) {
            headerText = "You have used 0 texts today.";
        }
        else {
            headerText = "You have used " + textsSent + " texts today.";
        }
        header.setText(headerText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = (TextView) findViewById(R.id.msgToday);
        feedButton = (Button) findViewById(R.id.feedButton);
        postButton = (Button) findViewById(R.id.postButton);
        searchButton = (Button) findViewById(R.id.searchButton);
        dmButton = (Button) findViewById(R.id.dmButton);
        helpButton = (Button) findViewById(R.id.helpButton);

        updateHeader();

        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
                updateHeader();
            }
        });

        feedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        dmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                Intent intent = new Intent(MainActivity.this, DmActivity.class);
                startActivity(intent);
                */
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d("onactivityresult", "updating header");
                numSms += data.getIntExtra("numSms", 0);
                updateHeader();
            }
            if (resultCode == Activity.RESULT_CANCELED && data == null) {
                Log.d("RESULT==CANCELED", "onActivityResult");
            }
        }
    }
}

