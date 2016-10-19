package com.xks.snackbardemo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void showSimpleSnackBar(final View view) {

        Snackbar.make(view, "已删除一个会话", Snackbar.LENGTH_SHORT)
                .setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(Main2Activity.this, "撤销了删除", Toast.LENGTH_SHORT).show();

                    }
                }).show();

       /* Snackbar.make(view, "Simple", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "Action Clicked");

            }
        }).setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                Log.i(TAG, "onDismissed " + event);
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                Log.i(TAG, "onShown");
            }
        }).show();*/

    }
}
