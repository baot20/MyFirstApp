package com.example.baot.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    int[] images = new int[] {
            R.drawable.image1,
            R.drawable.image2,
    };

    int currentImage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String ClickTimes = intent.getExtras().getString("ClickTimes");
        TextView textFromMain = (TextView) findViewById(R.id.textView2);
        textFromMain.setText(message + String.format(" This is main activity %S click", ClickTimes));
        textFromMain.setTextSize(20);

        RelativeLayout main = (RelativeLayout) findViewById(R.id.activity_display_message);
        final ImageView imageview = new ImageView(this);
        main.addView(imageview);
        imageview.setBaselineAlignBottom(true);

        imageview.setImageResource(images[0]);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview.setImageResource(images[++currentImage % images.length]);
                if (currentImage > 4) imageview.setVisibility(View.INVISIBLE);

            }
        });
     }

    public void goToCalculate(View view){
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }
}
