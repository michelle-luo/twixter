package com.example.ian.twixter;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class FeedActivity extends AppCompatActivity {
    private static FeedActivity inst;
    ListView smsListView;
    EditText inputSearch;
    Button helpFeed;
    ArrayList<Newsitem> smsMessagesList;
    private static CustomListAdapter myAdapter;

    public static FeedActivity instance() {
        return inst;
    }

    public void onStart() {
        super.onStart();
        inst = this;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        smsListView = (ListView) findViewById(R.id.SMSList);
        helpFeed = (Button) findViewById(R.id.helpFeed);
        smsMessagesList = new ArrayList<>();

        inputSearch = (EditText) findViewById(R.id.inputSearch);

        myAdapter = new CustomListAdapter(smsMessagesList, getApplicationContext());
        smsListView.setAdapter(myAdapter);

        helpFeed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(FeedActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("Here are all the tweets you've searched for. " +
                        "To add more tweets to this list, use the Search menu to find new " +
                        "things to discover.");
                helpDialog.setCancelable(true);

                helpDialog.setPositiveButton(
                        "Ok get it",
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
                                Intent intent = new Intent(FeedActivity.this, HelpActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = helpDialog.create();
                alert.show();
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        refreshSmsInbox();
    }

    public void refreshSmsInbox() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        assert smsInboxCursor != null;
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst())
            return;

        do {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Objects.equals(smsInboxCursor.getString(indexAddress), "7312567648")) {
                    Newsitem newsData = new Newsitem("", "");

                    String sms = smsInboxCursor.getString(indexBody).replace("Sent from your Twilio trial account - ", "");
                    String[] body = sms.split(" ", 2);
                    String username = body[0];
                    newsData.setUsername(username);
                    String tweet = body[1];
                    newsData.setFeed(tweet);
                    myAdapter.add(newsData);
                }
            }
        } while (smsInboxCursor.moveToNext());
    }

    public void updateList(final String smsMessage) {

        String[] body = smsMessage.split(" ", 2);
        String username = body[0];
        String tweet = body[1];
        Newsitem newTweet = new Newsitem(username, tweet);
        // newTweet.setUsername(username);
        // newTweet.setFeed(tweet);

        myAdapter.insert(newTweet, 0);
        myAdapter.notifyDataSetChanged();
    }

}
