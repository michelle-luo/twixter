package com.example.ian.twixter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    Button searchusername, searchhashtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchusername = (Button) findViewById(R.id.searchByUsername);
        searchhashtag = (Button) findViewById(R.id.searchByHashtag);

        searchusername.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SearchUsernameActivity.class);
                startActivity(intent);
            }
        });
    }
}
