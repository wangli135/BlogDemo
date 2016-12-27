package com.xks.viewgroupdemo.scroll;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xks.viewgroupdemo.R;

public class ViewScrollActivity extends AppCompatActivity {

    public static final String TAG = "ViewScroll";
    private TextView mScrollTv;

    private int times = 0;
    public static final int MSG_SET_X_Y = 1;
    public static final int MSG_SET_LAYOUT_PARAMS = 2;
    public static final int MSG_SCROLL_TEXT = 3;
    public static final int MSG_SCROLL_BY_PARENT = 4;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case MSG_SET_X_Y: {

                    if (times++ < 10)
                        scrollViewByChangeTranslationXY();

                }
                break;

                case MSG_SET_LAYOUT_PARAMS: {
                    if (times++ < 10)
                        scrollViewByChangeLayoutParams();
                }
                break;

                case MSG_SCROLL_TEXT: {
                    if (times++ < 10)
                        scrollText();
                }
                break;

                case MSG_SCROLL_BY_PARENT: {
                    if (times++ < 10)
                        scrollViewByParent();
                }
                break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scroll);

        mScrollTv = (TextView) findViewById(R.id.tv);

    }

    public void scrollView(View view) {

        times = 0;
        //方法 1
        //scrollViewByChangeTranslationXY();

        //方法2
//        scrollViewByChangeLayoutParams();

        //方法3
//        scrollViewByAnimator();

        //方法4
        scrollViewByParent();
    }

    public void scrollContent(View view) {
        times = 0;
        scrollText();
    }

    private void scrollText() {
        Log.i(TAG, "Prev ScrollX:" + mScrollTv.getScrollX());
        Log.i(TAG, "Prev ScrollY:" + mScrollTv.getScrollY());
        mScrollTv.scrollBy(-5, -5);
        mHandler.sendEmptyMessageDelayed(MSG_SCROLL_TEXT, 200);
        Log.i(TAG, "Current ScrollX:" + mScrollTv.getScrollX());
        Log.i(TAG, "Current ScrollY:" + mScrollTv.getScrollY());
    }

    //通过修改translationX/Y方法实现滑动
    private void scrollViewByChangeTranslationXY() {

        Log.i(TAG, "Prev X:" + mScrollTv.getX() + " Left:" + mScrollTv.getLeft() + " TranslationX:" + mScrollTv.getTranslationX());
        Log.i(TAG, "Prev Y:" + mScrollTv.getY() + " Top:" + mScrollTv.getTop() + " TranslationY:" + mScrollTv.getTranslationY());
        mScrollTv.setTranslationX(mScrollTv.getTranslationX() + 10);
        mScrollTv.setTranslationY(mScrollTv.getTranslationY() + 10);
        mHandler.sendEmptyMessageDelayed(MSG_SET_X_Y, 200);
        Log.i(TAG, "Current X:" + mScrollTv.getX() + " Left:" + mScrollTv.getLeft() + " TranslationX:" + mScrollTv.getTranslationX());
        Log.i(TAG, "Current Y:" + mScrollTv.getY() + " Top:" + mScrollTv.getTop() + " TranslationY:" + mScrollTv.getTranslationY());
    }

    //通过修改LayoutParams的Margin值实现滑动
    private void scrollViewByChangeLayoutParams() {
        Log.i(TAG, "Prev X:" + mScrollTv.getX() + " Left:" + mScrollTv.getLeft() + " TranslationX:" + mScrollTv.getTranslationX());
        Log.i(TAG, "Prev Y:" + mScrollTv.getY() + " Top:" + mScrollTv.getTop() + " TranslationY:" + mScrollTv.getTranslationY());
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mScrollTv.getLayoutParams();
        params.leftMargin = params.leftMargin + 10;
        params.topMargin = params.topMargin + 10;
        mScrollTv.setLayoutParams(params);
        mHandler.sendEmptyMessageDelayed(MSG_SET_LAYOUT_PARAMS, 200);
        Log.i(TAG, "Current X:" + mScrollTv.getX() + " Left:" + mScrollTv.getLeft() + " TranslationX:" + mScrollTv.getTranslationX());
        Log.i(TAG, "Current Y:" + mScrollTv.getY() + " Top:" + mScrollTv.getTop() + " TranslationY:" + mScrollTv.getTranslationY());
    }

    //通过动画首先滑动
    private void scrollViewByAnimator() {

        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mScrollTv, "translationX", 0, 100).setDuration(2000);
        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mScrollTv, "translationY", 0, 100).setDuration(2000);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(xAnimator, yAnimator);
        set.start();

    }

    //获取父布局控制View的移动
    private void scrollViewByParent() {
        View parent = findViewById(R.id.activity_view_scroll);
        parent.scrollBy(-5, -5);
        mHandler.sendEmptyMessageDelayed(MSG_SCROLL_BY_PARENT, 1000);
    }

}
