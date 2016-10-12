package com.xks.downloadmanager;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PIC_URL = "http://pic11.nipic.com/20101119/3320946_221711832717_2.jpg";
    public static final String APK_URL = "http://sw.bos.baidu.com/sw-search-sp/software/19de58890ffb8/QQ_8.6.18804.0_setup.exe";


    private DownloadManager mDownloadManager;

    private long id;

    private ImageView imageView;

    private DownloadReceiver mReceiver;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            query();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageview);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        mReceiver = new DownloadReceiver();
        registerReceiver(mReceiver, intentFilter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(PIC_URL));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        request.setTitle("下载图片...");
        id = mDownloadManager.enqueue(request);

        //延时是为了能查到数据
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /**
     * 查询操作
     */
    private void query() {

        DownloadManager.Query query = new DownloadManager.Query().setFilterById(id);
        Cursor cursor = mDownloadManager.query(query);

        if (cursor != null) {

            while (cursor.moveToNext()) {

                String bytesDownload = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                String descrition = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_DESCRIPTION));
                String id = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
                String localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                String mimeType = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE));
                String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                String status = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                String totalSize = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                Log.i("MainActivity", "bytesDownload:" + bytesDownload);
                Log.i("MainActivity", "descrition:" + descrition);
                Log.i("MainActivity", "id:" + id);
                Log.i("MainActivity", "localUri:" + localUri);
                Log.i("MainActivity", "mimeType:" + mimeType);
                Log.i("MainActivity", "title:" + title);
                Log.i("MainActivity", "status:" + status);
                Log.i("MainActivity", "totalSize:" + totalSize);

            }

        }

    }

    /**
     * 广播接收器，接受ACTION_DOWNLOAD_COMPLETE和ACTION_NOTICATION_CLICKED
     */
    class DownloadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

                Uri uri = mDownloadManager.getUriForDownloadedFile(id);

                imageView.setImageURI(uri);

            } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {

                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
