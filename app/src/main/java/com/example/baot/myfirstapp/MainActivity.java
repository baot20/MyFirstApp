package com.example.baot.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public final static  String ClickTimes ="onCreate";
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView3=(TextView) findViewById(R.id.textView3);
        textView3.setText(ClickTimes);

    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        i++;
          ;
        //textView set message
        TextView textView3=(TextView) findViewById(R.id.textView3);
        textView3.setText(String.format("This is my %s Click",String.valueOf(i)));

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //intent.putExtra(ClickTimes,i);
        startActivity(intent);

        //textView set message
        //TextView textView3=(TextView) findViewById(R.id.textView3);
        //textView3.setText(i);
    }
}
