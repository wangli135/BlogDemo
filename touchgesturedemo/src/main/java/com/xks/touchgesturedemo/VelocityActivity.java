package com.xks.touchgesturedemo;

import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

public class VelocityActivity extends AppCompatActivity {

    public static final String TAG = "Velocity";

    private VelocityTracker velocityTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                if (velocityTracker == null)
                    velocityTracker = VelocityTracker.obtain();
                else
                    velocityTracker.clear();

                velocityTracker.addMovement(event);

                break;

            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);

                Log.d(TAG, "X velocity: " +
                        VelocityTrackerCompat.getXVelocity(velocityTracker,
                                pointerId));
                Log.d(TAG, "Y velocity: " +
                        VelocityTrackerCompat.getYVelocity(velocityTracker,
                                pointerId));

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                velocityTracker.recycle();
                break;

        }

        return true;
    }
}
