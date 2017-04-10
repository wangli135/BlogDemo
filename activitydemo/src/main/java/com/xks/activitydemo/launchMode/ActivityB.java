package com.xks.activitydemo.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xks.activitydemo.R;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
    }

    //singleTop模式启动B
    public void singleTopStartB(View view) {
        startActivity(new Intent(this, ActivityB.class));
    }

}
