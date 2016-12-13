package com.xks.watermaskerdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView pic;
    private Bitmap originBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pic = (ImageView) findViewById(R.id.pic);
        originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        pic.setImageBitmap(originBitmap);

    }

    /**
     * 添加水印
     *
     * @param view
     */
    public void addWater(View view) {

        String text = "江山如此多娇";
        Bitmap bitmap = ImageUtil.addWaterMasker(originBitmap, text);
        pic.setImageBitmap(bitmap);

    }
}
