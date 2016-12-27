package com.xks.viewgroupdemo.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by Xingfeng on 2016-12-27.
 */

public class ScrollableImageView extends ImageView {

    private Scroller mScroller;

    public ScrollableImageView(Context context) {
        this(context, null);
    }

    public ScrollableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //Step 1:创建Scroller实例
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                if (!mScroller.isFinished())
                    return false;
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                //Step 2:调用startScroll完成滚动数据的初始化以及刷新界面
                smoothScrollTo(x, y);
                return true;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                return true;
        }

        return true;

    }

    private void smoothScrollTo(int destX, int destY) {
        int dx = destX - Math.abs(getScrollX());
        int dy = destY - Math.abs(getScrollY());
        mScroller.startScroll(getScrollX(), getScrollY(), -dx, -dy, 1000);
        invalidate();
    }

    //Step 3：重写computeScroll方法
    @Override
    public void computeScroll() {
        if (mScroller != null && mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
