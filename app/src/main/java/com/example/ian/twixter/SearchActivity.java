package com.example.ian.twixter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {
    Button searchUsername, searchHashtag, helpSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchUsername = (Button) findViewById(R.id.searchByUsername);
        searchHashtag = (Button) findViewById(R.id.searchByHashtag);
        helpSearch = (Button) findViewById(R.id.helpSearch);

        searchUsername.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SearchUsernameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        searchHashtag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SearchHashtagActivity.class);
                startActivity(intent);
                finish();
            }
        });

        helpSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(SearchActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("This is the help section for Search Page");
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
                                Intent intent = new Intent(SearchActivity.this, HelpActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = helpDialog.create();
                alert.show();
            }
        });
    }
}
