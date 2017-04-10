package com.example.ian.twixter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView header;
    String headerText;
    Button feedButton, postButton, searchButton, dmButton, helpButton;
    SendText sendSMS = new SendText();

    protected void updateHeader() {
        headerText = "You have sent " + Integer.toString(sendSMS.getSMSCount()) + " texts today.";
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
                intent.putExtra("sendSMS", (Parcelable)sendSMS);
                startActivity(intent);
                updateHeader();
            }
        });

        feedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                intent.putExtra("sendSMS", (Parcelable)sendSMS);
                startActivity(intent);
                updateHeader();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("sendSMS", (Parcelable)sendSMS);
                startActivity(intent);
                updateHeader();
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
                Intent intent = new Intent(MainActivity.this, DmActivity.class);
                startActivity(intent);
            }
        });
    }

}
