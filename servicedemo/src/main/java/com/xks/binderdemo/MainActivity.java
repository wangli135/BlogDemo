package com.xks.binderdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private TextView resultShowTv;

    private AddService.MyBinder myBinder;

    private Messenger messenger;

    private IRemoteService iRemoteService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i(TAG, "Service connected");
//            //服务端扩展Binder
//            myBinder = (AddService.MyBinder) service;
//            int result = myBinder.add(3, 5);
//            resultShowTv.setText(String.valueOf(result));

//            //服务端使用Messenger
            messenger = new Messenger(service);
            Message msg = Message.obtain(handler, 0, 5, 10);
            msg.replyTo = replyTo;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

//            iRemoteService = IRemoteService.Stub.asInterface(service);
//            try {
//                resultShowTv.setText(String.valueOf(iRemoteService.add(5, 6)));
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "Service disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultShowTv = (TextView) findViewById(R.id.result_show_tv);

    }

    //使用Binder
    public void bindAddService(View view) {

        Intent bindService = new Intent(this, AddService.class);
        bindService(bindService, connection, BIND_AUTO_CREATE);

    }

    //使用Messenger
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int sum = msg.arg1;
                    resultShowTv.setText(String.valueOf(sum));
                    break;
            }
        }
    };

    private Messenger replyTo = new Messenger(handler);

    public void messenger(View view) {
        Intent bindService = new Intent(this, MessengerService.class);
        bindService(bindService, connection, BIND_AUTO_CREATE);
    }

    //使用AIDL
    public void aidl(View view) {

        Intent bindService = new Intent(this, AidlService.class);
        bindService(bindService, connection, BIND_AUTO_CREATE);

    }
}
