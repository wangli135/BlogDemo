package com.xks.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * 服务中扩展Binder类，适合于应用与服务在同一个进程中
 */
public class AddService extends Service {

    static class MyBinder extends Binder {
        public int add(int a, int b) {
            return a + b;
        }
    }

    public AddService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


}
