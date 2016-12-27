package com.xks.binderdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
    }

    public void startIntentService(View view) {

        Intent intent = new Intent(this, MyIntentService.class);
        for (int i = 0; i < 10; i++) {
            intent.putExtra("num", i);
            startService(intent);
        }

    }
}
