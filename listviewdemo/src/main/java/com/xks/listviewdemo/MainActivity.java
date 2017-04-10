package com.xks.listviewdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QQZoneListView.OnHeaderViewListener {

    private List<String> datas = new ArrayList<>();
    private QQZoneListView mListView;
    private ArrayAdapter<String> mAdapter;
    private DynamicBgActionbar dynamicBgActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);

        dynamicBgActionbar = (DynamicBgActionbar) findViewById(R.id.dynamic_action_bar);
        mListView = (QQZoneListView) findViewById(R.id.qqzone_lv);
        mListView.setOnHeaderViewListener(this);

        initDatas();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(mAdapter);
    }

    private void initDatas() {
        for (int i = 'A'; i <= 'Z'; i++) {
            datas.add(String.valueOf((char) i));
        }
    }


    @Override
    public void onHeaderViewDismiss(View headerView) {
//        dynamicBgActionbar.setBackgroundResource(R.color.colorPrimary);
//        dynamicBgActionbar.setFitsSystemWindows(true);
//        dynamicBgActionbar.invalidate();
//
//        mListView.setFitsSystemWindows(false);
//        mListView.invalidate();
    }

    @Override
    public void onHeaderViewShow(View headerView) {
//        dynamicBgActionbar.setBackgroundColor(Color.TRANSPARENT);
//        dynamicBgActionbar.setFitsSystemWindows(false);
//        dynamicBgActionbar.invalidate();
//
//        mListView.setFitsSystemWindows(true);
//        mListView.invalidate();
    }
}
