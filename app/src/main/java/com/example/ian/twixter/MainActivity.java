package com.example.ian.twixter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    protected void updateHeader(int smsCount) {
        headerText = "You have used " + Integer.toString(smsCount) + " texts today.";
        header.setText(headerText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numSms = 0;

        header = (TextView) findViewById(R.id.msgToday);
        feedButton = (Button) findViewById(R.id.feedButton);
        postButton = (Button) findViewById(R.id.postButton);
        searchButton = (Button) findViewById(R.id.searchButton);
        dmButton = (Button) findViewById(R.id.dmButton);
        helpButton = (Button) findViewById(R.id.helpButton);

        updateHeader(numSms);

        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                int requestCode = 1;
                startActivityForResult(intent, requestCode);
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
                int requestCode = 1;
                startActivityForResult(intent, requestCode);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                */
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
                numSms += data.getIntExtra("numSms", 0);
                updateHeader(numSms);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.d("SMS CANCELED", "onActivityResult");
            }
        }
    }
}

