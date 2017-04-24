package com.example.ian.twixter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DmActivity extends AppCompatActivity {

    Button dmButton, cancelButton, helpDm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);

        dmButton = (Button) findViewById(R.id.dmButton);
        cancelButton = (Button) findViewById(R.id.dmButton2);
        helpDm = (Button) findViewById(R.id.helpDm);

        dmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        helpDm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder helpDialog = new AlertDialog.Builder(DmActivity.this);

                helpDialog.setTitle("Help");
                helpDialog.setMessage("This is the help section for Direct Message Page");
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
                                Intent intent = new Intent(DmActivity.this, HelpActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alert = helpDialog.create();
                alert.show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
