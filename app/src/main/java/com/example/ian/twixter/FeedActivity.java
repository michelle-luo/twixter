package com.example.ian.twixter;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class FeedActivity extends AppCompatActivity {
    private ListAdapter adapter;
    private static final Uri SMS_INBOX =
            Uri.parse("content://sms/inbox");
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Cursor c = getContentResolver().query(SMS_INBOX, null, null, null,null);
        startManagingCursor(c);
        String[] columns = new String[] {"body"};
        //int[] names = new int[] {R.id.row};
        //adapter = new SimpleCursorAdapter(this, R.layout.activity_feed, c, columns, names, 0);
        //setListAdapter(adapter);
        //setContentView(R.layout.activity_feed);
    }
}
