package com.example.ian.twixter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DmActivity extends AppCompatActivity {

    Button dmButton, dmButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);

        dmButton = (Button) findViewById(R.id.dmButton);
        dmButton2 = (Button) findViewById(R.id.dmButton2);

        dmButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
