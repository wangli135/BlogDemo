package com.xks.activitydemo.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xks.activitydemo.R;

public class ActivityD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
    }

    //standard模式启动A
    public void standardStartA(View view) {
        startActivity(new Intent(this, ActivityA.class));
    }
}
