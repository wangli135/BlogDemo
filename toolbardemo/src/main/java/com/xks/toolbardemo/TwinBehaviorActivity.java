package com.xks.toolbardemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TwinBehaviorActivity extends AppCompatActivity {

    TextView bortherTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twin_behavior);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bortherTv = (TextView) findViewById(R.id.leftTv);
    }

    float scale = 1.1f;

    public void changeSize(View view) {

        bortherTv.setScaleX(scale);
        bortherTv.setScaleY(scale);
        scale += 0.1f;

    }

    public void changePosition(View view) {
        ViewCompat.offsetTopAndBottom(bortherTv, 5);
    }


}
