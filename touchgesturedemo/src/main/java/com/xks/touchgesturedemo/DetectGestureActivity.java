package com.xks.touchgesturedemo;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class DetectGestureActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public static final String ACTICITY_TAG = "activity";
    public static final String VIEW_TAG = "view";
    public static final String GESTURE_TAG = "gesture";

    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_gesture);

        View view = findViewById(R.id.show);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
//                        Log.i(VIEW_TAG, "action down");
//                        return true;
//
//                    case MotionEvent.ACTION_MOVE:
//                        Log.i(VIEW_TAG, "action move");
//                        return true;
//
//                    case MotionEvent.ACTION_UP:
//                        Log.i(VIEW_TAG, "action up");
//                        return true;
//
//                    case MotionEvent.ACTION_OUTSIDE:
//                        Log.i(VIEW_TAG, "action outside");
//                        return true;
//
//                    default:
//                        return false;
//
//
//                }

                //使用GestureDetectorCompat
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });


//        gestureDetectorCompat = new GestureDetectorCompat(this, this);
//        gestureDetectorCompat.setOnDoubleTapListener(this);

        //使用自定义手势检测器
        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        /*int action = MotionEventCompat.getActionMasked(event);

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                Log.i(ACTICITY_TAG, "action down");
                return true;

            case MotionEvent.ACTION_MOVE:
                Log.i(ACTICITY_TAG, "action move");
                return true;

            case MotionEvent.ACTION_UP:
                Log.i(ACTICITY_TAG, "action up");
                return true;

            case MotionEvent.ACTION_CANCEL:
                Log.i(ACTICITY_TAG, "action cancel");
                return true;

            case MotionEvent.ACTION_OUTSIDE:
                Log.i(ACTICITY_TAG, "action outside");
                return true;

            default:
                return super.onTouchEvent(event);

        }*/

        //使用GestureDetectorCompat
        gestureDetectorCompat.onTouchEvent(event);
        return true;

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(GESTURE_TAG, "onDoubleTab");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i(GESTURE_TAG, "onDoubleTabEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(GESTURE_TAG, "onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(GESTURE_TAG, "onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(GESTURE_TAG, "onSingleTabUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(GESTURE_TAG, "onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(GESTURE_TAG, "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i(GESTURE_TAG, "onFling");
        return true;
    }

    /**
     * 继承GestureDetector.SimpleOnGestureListener类，重写感兴趣的方法
     */
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        public static final String TAG = "mygesturelistener";

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(TAG, "onDown");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling x:" + velocityX + " y:" + velocityY);
            return true;
        }
    }
}
