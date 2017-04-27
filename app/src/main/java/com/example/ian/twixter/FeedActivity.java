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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class FeedActivity extends AppCompatActivity {

    private static FeedActivity inst;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    ListView smsListView;
    Button helpFeed;
    ArrayAdapter<String> arrayAdapter;

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
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smsMessagesList);
        smsListView.setAdapter(arrayAdapter);

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

        arrayAdapter.clear();

        do {
            // Log.d("REFRESH SMS", smsInboxCursor.getString(indexAddress));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Objects.equals(smsInboxCursor.getString(indexAddress), "7312567648")) {
                    String sms = smsInboxCursor.getString(indexBody);
                    String[] body = sms.split(" ", 2);
                    String username = body[0];
                    String tweet = body[1];
                    String str = username + ":\n" + tweet + "\n";
                    arrayAdapter.add(str);
                }
            }
        } while (smsInboxCursor.moveToNext());
    }

    public void updateList(final String smsMessage) {
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }
}
