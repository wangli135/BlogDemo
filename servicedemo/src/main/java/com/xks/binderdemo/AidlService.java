package com.xks.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlService extends Service {

    static class AidlBinder extends IRemoteService.Stub {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    public AidlService() {
    }

    private AidlBinder aidlBinder = new AidlBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return aidlBinder;
    }
}
