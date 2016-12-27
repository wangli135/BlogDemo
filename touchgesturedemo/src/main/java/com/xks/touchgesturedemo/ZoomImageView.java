package com.xks.touchgesturedemo;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by Xingfeng on 2016-12-19.
 */

public class ZoomImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener {

    public static final String TAG = "ZoomImageView";

    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector mGestureDetector;

    private Matrix mScaleMatrix = new Matrix();
    private float mInitialScale = 1.0f;
    private final float MAX_FACTOR = 4.0f;

    /**
     * 用于存放矩阵的9个值
     */
    private final float[] matrixValues = new float[9];

    private boolean once = true;

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setScaleType(ScaleType.MATRIX);

        mScaleGestureDetector = new ScaleGestureDetector(context, mSimpleOnScaleGestureListenr);
        mGestureDetector = new GestureDetector(context, mSimpleGestureDetector);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = mScaleGestureDetector.onTouchEvent(event);
        retVal = mGestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    @Override
    public void onGlobalLayout() {

        if (once) {

            Drawable d = getDrawable();
            if (d == null)
                return;
            Log.i(TAG, d.getIntrinsicWidth() + ", " + d.getIntrinsicHeight());
            int width = getWidth();
            int height = getHeight();
            Log.i(TAG, width + ", " + height);
            //拿到图片的宽和高
            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            float scale = 1.0f;
            //如果图片的宽或者高大于屏幕，则缩放至屏幕
            if (dw > width && dh <= height) {
                scale = width * 1.0f / dw;
            }
            if (dh > height && dw <= width) {
                scale = height * 1.0f / dh;
            }
            //如果宽和高都大于屏幕，则让其按比例适应屏幕大小
            if (dw > width && dh > height) {
                scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
            }
            mInitialScale = scale;
            //图片移动至屏幕中心
            mScaleMatrix.postTranslate((width - dw) / 2, (height - dh) / 2);
            mScaleMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mScaleMatrix);
            once = false;

        }

    }

    /**
     * 获得当前的缩放比例
     *
     * @return
     */
    private float getScale() {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    private ScaleGestureDetector.SimpleOnScaleGestureListener mSimpleOnScaleGestureListenr = new ScaleGestureDetector.SimpleOnScaleGestureListener() {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scale = getScale();
            float scaleFactor = detector.getScaleFactor();
            Log.i(TAG, "Scale Factor " + scaleFactor);

            if (getDrawable() == null)
                return true;

            //前一段符合放大条件,后一段符合缩小要求
            if ((scale < MAX_FACTOR && scaleFactor >= 1.0f) || (scale > mInitialScale && scaleFactor <= 1.0f)) {

                //最大值、最小值判断
                if (scaleFactor * scale < mInitialScale)
                    scaleFactor = mInitialScale / scale;
                if (scaleFactor * scale > MAX_FACTOR)
                    scaleFactor = MAX_FACTOR / scale;
                mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
                checkBorderAndCenterWhenScale();
                setImageMatrix(mScaleMatrix);
            }


            return true;
        }

    };

    private GestureDetector.SimpleOnGestureListener mSimpleGestureDetector = new GestureDetector.SimpleOnGestureListener() {

        private float lastX;
        private float lastY;

        @Override
        public boolean onDown(MotionEvent e) {
            lastX = e.getX();
            lastY = e.getY();
            return true;
        }

        //双击放大
        @Override
        public boolean onDoubleTap(MotionEvent e) {

            float pivotX = e.getX();
            float pivotY = e.getY();

            float scale = getScale();
            float scaleFactor = 2;
            Log.i(TAG, "Scale " + scale);
            if (scale < MAX_FACTOR) {
                if (scale * 2 > MAX_FACTOR)
                    scaleFactor = MAX_FACTOR / scale;
                mScaleMatrix.postScale(scaleFactor, scaleFactor, pivotX, pivotY);
                checkBorderAndCenterWhenScale();
                setImageMatrix(mScaleMatrix);
            }
            return true;
        }

    };

    /**
     * 在缩放时进行图片显示范围的控制
     */
    private void checkBorderAndCenterWhenScale() {

        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        //如果宽度或高大于屏幕，则控制范围
        if (rectF.width() >= width) {

            if (rectF.left > 0)
                deltaX = -rectF.left;
            if (rectF.right < width)
                deltaX = width - rectF.right;

        }
        if (rectF.height() >= height) {
            if (rectF.top > 0)
                deltaY = -rectF.top;
            if (rectF.bottom < height)
                deltaY = height - rectF.bottom;
        }

        //如果宽或高小于屏幕，则让其居中
        if (rectF.width() < width) {
            deltaX = width * 0.5f - rectF.left - 0.5f * rectF.width();
        }
        if (rectF.height() < height) {
            deltaY = height * 0.5f - rectF.top - 0.5f * rectF.height();
        }
        Log.i(TAG, "deltaX=" + deltaX + ", deltaY=" + deltaY);
        mScaleMatrix.postTranslate(deltaX, deltaY);

    }

    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();
        Drawable d = getDrawable();
        if (d != null) {
            rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

}
