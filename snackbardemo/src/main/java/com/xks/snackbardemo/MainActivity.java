package com.xks.snackbardemo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSimpleSnackBar(final View view) {

        Snackbar.make(view, "已删除一个会话", Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {

                switch (event) {

                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                    case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                        //TODO 网络操作
                        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                        Toast.makeText(MainActivity.this, "撤销了删除操作", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                Log.i(TAG, "onShown");
            }
        }).show();

    }
}
