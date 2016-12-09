package com.xks.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * 使用Messenger，适合应用与服务在不同进程中
 */
public class MessengerService extends Service {

    //Handler用于处理客户端发送来的消息，不存在线程安全问题
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0:
                    int a = msg.arg1;
                    int b = msg.arg2;
                    int sum = a + b;
                    //得到客户端的Messenger，回复消息
                    Messenger replyTo = msg.replyTo;
                    Message message = Message.obtain();
                    message.what = 1;
                    message.arg1 = sum;
                    try {
                        replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    };

    private Messenger messenger = new Messenger(mHandler);

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //返回Messenger内部的Binder
        return messenger.getBinder();
    }
}
