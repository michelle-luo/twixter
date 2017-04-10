package com.example.ian.twixter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    TextView whatIsText, howToText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        whatIsText = (TextView) findViewById(R.id.whatIsHeaderText);
        howToText = (TextView) findViewById(R.id.howToHeaderText);

        whatIsText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}
