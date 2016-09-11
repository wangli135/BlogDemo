package com.xks.textspan;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;

/**
 * 有序段落格式
 * Created by Xingfeng on 2016-09-11.
 */
public class OrderSpan implements LeadingMarginSpan {

    private final int mOrder;//序号
    private int mOrderColor;//序号颜色
    private int mGapWidth;

    private boolean hasSetColor;

    public static final int STANDARD_GAP_WIDTH = 20;

    public OrderSpan() {
        this(1);
    }

    public OrderSpan(int mOrder) {
        this(mOrder, STANDARD_GAP_WIDTH);
    }

    public OrderSpan(int mOrder, int mGapWidth) {
        this.mOrder = mOrder;
        this.mGapWidth = mGapWidth;
        hasSetColor = false;
    }

    public OrderSpan(int mOrder, int mOrderColor, int mGapWidth) {
        this.mOrder = mOrder;
        this.mOrderColor = mOrderColor;
        this.mGapWidth = mGapWidth;
        hasSetColor = true;
    }

    /**
     * 返回Margin的宽度，文本宽度+gapWidth
     *
     * @param first
     * @return
     */
    @Override
    public int getLeadingMargin(boolean first) {
        return 20 + mGapWidth;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {

        if (((Spanned) text).getSpanStart(this) == start) {
            Paint.Style style = p.getStyle();
            int oldcolor = p.getColor();

            if (hasSetColor) {
                p.setColor(mOrderColor);
            }


            String orderText = String.valueOf(mOrder);
            c.drawText(orderText, 0, orderText.length(), x, baseline, p);

            p.setColor(oldcolor);
            p.setStyle(style);
        }


    }
}
