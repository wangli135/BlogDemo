package com.xks.textspan;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * 用文字替换文字
 * Created by Xingfeng on 2016-09-11.
 */
public class TextReplacementSpan extends ReplacementSpan {

    private CharSequence mRelaceText;
    private int mReplaceTextColor;
    private int mReplaceTextSize;

    public TextReplacementSpan(CharSequence mRelaceText) {
        this(mRelaceText, Color.BLACK, 30);
    }

    public TextReplacementSpan(CharSequence mRelaceText, int mReplaceTextColor, int mReplaceTextSize) {
        this.mRelaceText = mRelaceText;
        this.mReplaceTextColor = mReplaceTextColor;
        this.mReplaceTextSize = mReplaceTextSize;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {

        paint.setColor(mReplaceTextColor);
        paint.setTextSize(mReplaceTextSize);
        int width = (int) paint.measureText(mRelaceText, 0, mRelaceText.length());
        if (fm != null) {
            fm.ascent = (int) paint.ascent();
            fm.descent = (int) paint.descent();
            fm.top = fm.ascent;
            fm.bottom = fm.descent;
        }

        return width;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        paint.setTextSize(mReplaceTextSize);
        paint.setColor(mReplaceTextColor);
        canvas.drawText(mRelaceText, 0, mRelaceText.length(), x, y, paint);
    }

}
