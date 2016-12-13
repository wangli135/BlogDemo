package com.xks.viewgroupdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Xingfeng on 2016-12-13.
 */

public class TabBar extends FrameLayout {

    private Button leftBtn, rightBtn;
    private TextView titleTv;

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.tabbar_layout, this);
        leftBtn = (Button) findViewById(R.id.left_btn);
        titleTv = (TextView) findViewById(R.id.middle_title);
        rightBtn = (Button) findViewById(R.id.right_btn);

        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabBarBtnClickListener != null) {
                    tabBarBtnClickListener.onLeftBtnClick((Button) v);
                }
            }
        });
        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabBarBtnClickListener != null)
                    tabBarBtnClickListener.onRightBtnClick((Button) v);
            }
        });

    }


    //最简单的方式，将三个按钮都可以供用户操作

    public Button getLeftBtn() {
        return leftBtn;
    }

    public Button getRightBtn() {
        return rightBtn;
    }

    public TextView getTitleTv() {
        return titleTv;
    }

    //只暴露操作，不暴露控件

    /**
     * 设置标题文本
     *
     * @param charSequence
     */
    public void setTitleText(CharSequence charSequence) {
        titleTv.setText(charSequence);
    }

    public void setRightBtnVisibility(int visibility) {
        rightBtn.setVisibility(visibility);
    }

    //设置接口回调

    interface TabBarBtnClickListener {
        void onLeftBtnClick(Button leftBtn);

        void onRightBtnClick(Button rightBtn);
    }

    private TabBarBtnClickListener tabBarBtnClickListener;

    public void setTabBarBtnClickListener(TabBarBtnClickListener tabBarBtnClickListener) {
        this.tabBarBtnClickListener = tabBarBtnClickListener;
    }
}
