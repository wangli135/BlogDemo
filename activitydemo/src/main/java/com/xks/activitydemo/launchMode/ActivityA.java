package com.xks.activitydemo.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xks.activitydemo.R;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    //standard模式启动A
    public void standardStartA(View view) {
        startActivity(new Intent(this, ActivityA.class));
    }

    //singleTop模式启动B
    public void singleTopStartB(View view) {
        startActivity(new Intent(this, ActivityB.class));
    }

    //singleTask模式启动C
    public void singleTaskStartC(View view) {
        startActivity(new Intent(this, ActivityC.class));
    }

    //singleInstance模式启动D
    public void singleInstanceStartD(View view) {
        startActivity(new Intent(this, ActivityD.class));
    }
}
