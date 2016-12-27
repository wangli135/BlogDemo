package com.xks.touchgesturedemo;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * Created by Xingfeng on 2016-12-16.
 */

public class DragableImageView extends ImageView {

    public DragableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private final int INVAILID_POINTER_ID = 900;
    private int mActivePointerId = INVALID_POINTER_ID;

    private float mLastX = 0;
    private float mLastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {

            case MotionEvent.ACTION_DOWN: {
                int pointerIndex = event.getActionIndex();
                float x = event.getX(pointerIndex);
                float y = event.getY(pointerIndex);

                mLastX = x;
                mLastY = y;

                mActivePointerId = event.getPointerId(0);

                break;
            }


            case MotionEvent.ACTION_MOVE: {

                int pointerIndex = event.getActionIndex();
                float x = event.getX(pointerIndex);
                float y = event.getY(pointerIndex);

                setX(x);
                setY(y);

//                float dx = x - mLastX;
//                float dy = y - mLastY;
//
//                scrollBy(-(int) dx, -(int) dy);

                mLastX = x;
                mLastY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                int pointerIndex = event.getActionIndex();
                int pointerId = event.getPointerId(pointerIndex);

                if (pointerId == mActivePointerId) {

                    int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastX = event.getX(newPointerIndex);
                    mLastY = event.getY(newPointerIndex);
                    mActivePointerId = event.getPointerId(newPointerIndex);

                }

                break;

            }

        }

        return true;
    }

}
