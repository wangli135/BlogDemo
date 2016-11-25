package com.xks.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //Handler收到了非UI线程的消息后，更新UI，因为这个Handler是在主线程中的
                case 1:

                    showTv.setText("Hello,Handler");

                    break;

                //Handler收到了计时器中的消息
                case 2:

                    initVal--;
                    if (initVal < 0)
                        break;

                    timerTv.setText(initVal + "s");
                    //下一秒继续计时
                    startTimer();


                    break;

                default:
                    break;
            }
        }
    };

    private Handler handler2;

    private TextView showTv;
    private TextView timerTv;

    private int initVal = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTv = (TextView) findViewById(R.id.tv_show);
        timerTv = (TextView) findViewById(R.id.tv_timer);
        timerTv.setText(initVal + "s");

//        doWork();

//        startTimer();

        //非主线程使用Handler
        initHandler();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        otherThread();
    }

    //非UI线程初始化Handler
    private void initHandler() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //Step 1:调用Looper.prepare()
                Looper.prepare();
                //Step 2:实例化Handler，重写handleMessage方法
                handler2 = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {

                            case 1:

                                Log.i("MainActivity", "Other Thread Completed");

                                break;

                        }
                    }
                };
                //Step 3:调用Looper.loop()
                Looper.loop();

            }
        }).start();
    }

    private void otherThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //模拟耗时任务
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //发送消息
                handler2.sendEmptyMessage(1);

            }
        }).start();

    }

    /**
     * 通过Handler的定时消息实现计时器
     */
    private void startTimer() {

        //1s之后的消息
        handler.sendEmptyMessageDelayed(2, 1000);

    }

    /**
     * Handler使用场景1——更新UI
     * 开启一个线程执行耗时操作，执行完之后，通过Handler发送一个消息
     */
    private void doWork() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //模拟耗时操作
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(1);


            }
        }).start();

    }
}
