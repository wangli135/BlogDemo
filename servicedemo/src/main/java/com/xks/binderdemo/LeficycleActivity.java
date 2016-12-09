package com.xks.binderdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LeficycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leficycle);
    }

    public void startLoggerService(View view) {
        startService(new Intent(this, LoggerService.class));
    }

    public void stopLoggerService(View view) {
        stopService(new Intent(this, LoggerService.class));
    }

    private int i=1;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(LoggerService.TAG, "Service connected "+i++);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(LoggerService.TAG, "Service disconnected");
        }
    };

    public void bindLoggerService(View view) {
        bindService(new Intent(this, LoggerService.class), connection, Service.BIND_AUTO_CREATE);
    }

    public void unbindLoggerService(View view) {
        unbindService(connection);
    }
}
