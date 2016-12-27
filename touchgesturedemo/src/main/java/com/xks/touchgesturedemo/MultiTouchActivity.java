package com.xks.touchgesturedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MultiTouchActivity extends AppCompatActivity {

    public static final String TAG = "MultiTouch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int index = event.getActionIndex();
//        int action = event.getActionMasked();
//
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.i(TAG, "index:" + index+" down");
//                break;
//
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.i(TAG, "index:" + index+" pointer down");
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                Log.i(TAG, "index:" + index+" move");
//                break;
//
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.i(TAG, "index:" + index+" pointer up");
//                break;
//
//            case MotionEvent.ACTION_UP:
//                Log.i(TAG, "index:" + index+" up");
//                break;
//
//        }
//
//        return true;
//    }
}
