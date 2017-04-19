package com.example.ian.twixter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    Button searchUsername, searchHashtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchUsername = (Button) findViewById(R.id.searchByUsername);
        searchHashtag = (Button) findViewById(R.id.searchByHashtag);

        searchUsername.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SearchUsernameActivity.class);
                startActivity(intent);
            }
        });

        searchHashtag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SearchHashtagActivity.class);
                // intent.putExtra("sendSMS", (Parcelable)sendSMS);
                startActivity(intent);
            }
        });
    }
}
