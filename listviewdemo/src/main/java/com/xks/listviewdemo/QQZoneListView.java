package com.xks.listviewdemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Xingfeng on 2017-03-19.
 */

public class QQZoneListView extends ListView implements AbsListView.OnScrollListener {

    private View headerView;

    private OnHeaderViewListener mOnHeaderViewListener;

    public QQZoneListView(Context context) {
        this(context, null);
    }

    public QQZoneListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQZoneListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        headerView = initHeaderView(context);
        addHeaderView(headerView);

        setOnScrollListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public QQZoneListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initHeaderView(context);
        addHeaderView(headerView);

        setOnScrollListener(this);

    }

    protected View initHeaderView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_header_view, this, false);
        return view;

    }

    public void setOnHeaderViewListener(OnHeaderViewListener mOnHeaderViewListener) {
        this.mOnHeaderViewListener = mOnHeaderViewListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (mOnHeaderViewListener != null) {
            if (firstVisibleItem == 0) {
                mOnHeaderViewListener.onHeaderViewShow(headerView);
            } else if (firstVisibleItem == 1) {
                mOnHeaderViewListener.onHeaderViewDismiss(headerView);
            }
        }


    }

    public interface OnHeaderViewListener {
        void onHeaderViewDismiss(View headerView);

        void onHeaderViewShow(View headerView);
    }
}
