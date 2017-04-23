package com.example.ian.twixter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

public class SearchHashtagActivity extends AppCompatActivity {
    Button searchHashButton, cancelHashButton;
    SearchView search;
    EditText numTexts;
    SendText sendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hashtag);

        searchHashButton = (Button) findViewById(R.id.searchHashButton);
        cancelHashButton = (Button) findViewById(R.id.cancelHashButton);
        search = (SearchView) findViewById(R.id.simpleSearchView);
        numTexts = (EditText) findViewById(R.id.numTextBox);

        // sendSMS = getIntent().getExtras().getParcelable("sendSMS");
        // fix this lol
        sendSMS = new SendText();

        searchHashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String parameter = search.getQuery().toString();
                parameter = parameter.replaceAll("/[^\\w]/", "");
                int numberOfTexts = Integer.parseInt(numTexts.getText().toString());
                if (numberOfTexts > 10) {
                    numberOfTexts = 10;
                }
                String msg = "sh " + numberOfTexts + " " + parameter;

                /* send message */
                sendSMS = sendSMS.sendText(getBaseContext(), "+17312567648", msg);

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        // if you are redirecting from a fragment then use getActivity() as the context.
                        startActivity(new Intent(SearchHashtagActivity.this, FeedActivity.class));
                        finish();
                    }
                };

                Handler h = new Handler();
                // The Runnable will be executed after the given delay time
                h.postDelayed(r, 1000); // will be delayed for 1 second
            }
        });

        cancelHashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
