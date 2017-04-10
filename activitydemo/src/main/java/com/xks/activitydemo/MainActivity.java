package com.xks.activitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LifeCycle";

    private int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        if (savedInstanceState != null) {
            val = savedInstanceState.getInt(TAG);
        }
        Log.i(TAG, "val=" + val);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        val = 1;
        Log.i(TAG, "val=" + val);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        val = 2;
        Log.i(TAG, "val=" + val);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        val = 3;
        Log.i(TAG, "val=" + val);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        val = 4;
        Log.i(TAG, "val=" + val);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory");
        val = 5;
        Log.i(TAG, "val=" + val);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
        val = 6;
        Log.i(TAG, "val=" + val);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(TAG, val);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
        Log.i(TAG, "val=" + val);
    }

    public void startSecondActivity(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
