package com.xks.viewgroupdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TagLayout tagLayout = (TagLayout) findViewById(R.id.activity_main);
        TextView textView = new TextView(this);
        textView.setText("Android");
        textView.setTextSize(40);
        tagLayout.addView(textView);

    }

}
