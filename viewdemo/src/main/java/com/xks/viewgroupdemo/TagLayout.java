package com.xks.viewgroupdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 标签布局
 * Created by Xingfeng on 2016-10-20.
 */
public class TagLayout extends ViewGroup {
    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 计算高度和宽度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int count = getChildCount();

        int lineHeight = 0;
        int lineWidth = 0;

        int width = 0;
        int height = 0;

        //遍历子View
        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);

            //测量子View
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            //得到子View占据的宽度和高度，包括marigin
            int w = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int h = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            //如果一行宽度超过了TagLayout的宽度，不包括左右padding
            if (lineWidth + w > widthSize - getPaddingLeft() - getPaddingRight()) {

                //高度加上上一行的高度
                height += lineHeight;
                //高度取所有行中最宽的
                width = Math.max(width, lineWidth);
                lineHeight = h;
                lineWidth = w;

            }
            //不需要换行
            else {
                //每一行的高度以最大的高度为准
                lineHeight = Math.max(lineHeight, h);
                lineWidth += w;
            }

            //如果是最后一个View，因为可能没有转行，所以要对宽度做个判断，有可能最后一行就是最宽的，总的高度需要加上最后一行的高度
            if (i == count - 1) {

                width = Math.max(width, lineWidth);
                height += lineHeight;

            }

        }

        //确定宽高，如果是确定的，则使用约束的，否则使用计算得到值
        int w = widthMode == MeasureSpec.EXACTLY ? widthSize : width + getPaddingLeft() + getPaddingRight();
        int h = heightMode == MeasureSpec.EXACTLY ? heightSize : height + getPaddingTop() + getPaddingBottom();

        //千万记得调用该方法
        setMeasuredDimension(w, h);


    }

    /**
     * 对子View进行位置安放
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //安放的起始位置是左上角，去除左和上padding部分
        int left = getPaddingLeft();
        int top = getPaddingTop();


        int lineWidth = 0;
        int lineHeight = 0;

        int width = getWidth();

        int count = getChildCount();
        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int vWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int vHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;


            //换行
            if (vWidth + lineWidth > width - getPaddingRight() - getPaddingLeft()) {

                //计算下一行的高度
                top += lineHeight;
                lineWidth = vWidth;
                //下一行左边的开始
                left = getPaddingLeft();


            } else {

                //每一行高度取最大的一个
                lineHeight = Math.max(lineHeight, vHeight);
                //行宽
                lineWidth += vWidth;

            }

            child.layout(left + lp.leftMargin, top + lp.topMargin, left + +lp.leftMargin + child.getMeasuredWidth(), top + lp.topMargin + child.getMeasuredHeight());

            //左起始
            left += vWidth;

        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 用于支持addView方法
     * @return
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(200,700);
    }
}
