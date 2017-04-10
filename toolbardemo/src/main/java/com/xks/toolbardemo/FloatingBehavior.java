package com.xks.toolbardemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Xingfeng on 2017-03-23.
 */

public class FloatingBehavior extends FloatingActionButton.Behavior {

    private boolean isAniming;

    public FloatingBehavior() {
        super();
    }

    public FloatingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
//        return dependency instanceof NestedScrollView;
//    }
//
//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
//
//        if (dependency instanceof NestedScrollView) {
//
//            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//            int fab_bm = params.bottomMargin;
//            int distance = child.getHeight() + fab_bm;
//            child.setY(dependency.getY() - distance);
//        }
//
//        return super.onDependentViewChanged(parent, child, dependency);
//    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    //向上滑，dyConsumed>0；向下滑，dyConsumed<0
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0 && !isAniming && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (dyConsumed < 0 && !isAniming && child.getVisibility() == View.INVISIBLE) {
            show(child);
        }
    }

    private void show(final FloatingActionButton child) {

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedValue();
                child.setScaleX(fraction);
                child.setScaleY(fraction);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                child.setVisibility(View.VISIBLE);
                isAniming = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAniming = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAniming = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();

    }

    private void hide(final View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(1, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                view.setScaleX(f);
                view.setScaleY(f);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAniming = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
                isAniming = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAniming = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
