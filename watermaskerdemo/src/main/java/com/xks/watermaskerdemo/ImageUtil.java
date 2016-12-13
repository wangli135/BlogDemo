package com.xks.watermaskerdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextPaint;

/**
 * Created by Xingfeng on 2016-11-30.
 */

public class ImageUtil {

    /**
     * 给图片添加文字水印
     *
     * @param src  原图
     * @param text 文字
     * @return 添加水印后的图片
     */
    public static Bitmap addWaterMasker(Bitmap src, String text) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newb);
        cv.drawBitmap(src, 0, 0, null);
        if (text != null) {
            String familyName = "宋体";
            Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.RED);
            textPaint.setTypeface(font);
            textPaint.setTextSize(100);
            cv.drawText(text, 50, 100, textPaint);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return newb;
    }

}
