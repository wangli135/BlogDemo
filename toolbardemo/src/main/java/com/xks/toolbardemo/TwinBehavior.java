package com.xks.toolbardemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Xingfeng on 2017-03-23.
 */

public class TwinBehavior extends CoordinatorLayout.Behavior<View> {


    public TwinBehavior() {
        super();
    }

    public TwinBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //只要两个View是同一种View即可
        boolean isTwin = child.getClass() == dependency.getClass();
        return isTwin;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //主要监听大小
        float scaleX = dependency.getScaleX();
        float scaleY = dependency.getScaleY();
        child.setScaleX(scaleX);
        child.setScaleY(scaleY);

        //监听位置的变化
        int top = dependency.getTop();
        int distance = top - child.getTop();
        ViewCompat.offsetTopAndBottom(child, distance);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //是否允许滚动，只要两个类是NestedScrollView并且滚动方向是垂直即可
        return child instanceof NestedScrollView && directTargetChild.getClass() == child.getClass() && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int scrollY = target.getScrollY();
        child.setScrollY(scrollY);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {

        //支持Fling
        ((NestedScrollView) child).fling((int) velocityY);
        return true;
    }
}
