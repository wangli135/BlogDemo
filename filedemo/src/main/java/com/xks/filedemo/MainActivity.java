package com.xks.filedemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.show);

        sb.append("ExternalCacheDir：" + getExternalCacheDir().getAbsolutePath() + "\n")
                .append("CacheDir：" + getCacheDir().getAbsolutePath() + "\n");
        sb.append("FilesDir：" + getFilesDir().getAbsolutePath() + "\n");
        sb.append("Dir：" + getDir("hello", MODE_PRIVATE).getAbsolutePath() + "\n");
        sb.append("FileList：" + fileList() + "\n");
        for (String s : fileList()) {
            sb.append(s + "\n");
        }

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            sb.append("DataDirectory：" + Environment.getDataDirectory().getAbsolutePath() + "\n");
            sb.append("DownloadCacheDirectory：" + Environment.getDownloadCacheDirectory().getAbsolutePath() + "\n");
            sb.append("ExternalStorageDirectory()：" + Environment.getExternalStorageDirectory().getAbsolutePath() + "\n");
            sb.append("ExternalStoragePublicDirectory：" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "\n");
            sb.append("RootDirectory：" + Environment.getRootDirectory().getAbsolutePath() + "\n");
            sb.append("File:" + new File("test").getAbsolutePath() + "\n");
            sb.append("ExternalFilesDir:" + getExternalFilesDir(null).getAbsolutePath() + "\n");
        }

        try {
            FileOutputStream fous = openFileOutput("hello", MODE_PRIVATE);
            fous.write("hello world".getBytes());
            fous.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView.setText(sb);

    }
}
