package com.xks.listviewdemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Xingfeng on 2017-03-19.
 */

public class DynamicBgActionbar extends FrameLayout {

    View view;

    public DynamicBgActionbar(Context context) {
        this(context, null);
    }

    public DynamicBgActionbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicBgActionbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View root = LayoutInflater.from(context).inflate(R.layout.layout_dynamic_bg_actionbar, this);
        view = root.findViewById(R.id.dynamic_action_bar);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DynamicBgActionbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View root = LayoutInflater.from(context).inflate(R.layout.layout_dynamic_bg_actionbar, this);
        view = root.findViewById(R.id.dynamic_action_bar);
    }

//    @Override
//    public void setBackgroundResource(int resid) {
//        view.setBackgroundResource(resid);
//    }
//
//    @Override
//    public void setBackgroundColor(@ColorInt int color) {
//        view.setBackgroundColor(color);
//    }
}
