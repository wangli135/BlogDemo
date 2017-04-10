package com.xks.toolbardemo;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Xingfeng on 2017-03-20.
 */

public class ToolbarHelper {

    public static void addMiddleTitle(Context context, CharSequence title, Toolbar toolbar) {

        TextView textView = new TextView(context);
        textView.setText(title);

        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        toolbar.addView(textView, params);

    }

}
