package com.example.baot.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textForClickTimes = (TextView) findViewById(R.id.textView2);
        textForClickTimes.setText(message);


        /*
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);


        TextView textView5 = new TextView(this);
        textView5.setTextSize(40);
        textView5.setText("Static String");
        //textView5.setText(intent.getStringExtra(MainActivity.ClickTimes));



        TextView textForClickTimes = (TextView) findViewById(R.id.textView2);
        textForClickTimes.setText("Static String");

        textForClickTimes.setText(intent.getStringExtra(MainActivity.ClickTimes));
        */

    }
}
