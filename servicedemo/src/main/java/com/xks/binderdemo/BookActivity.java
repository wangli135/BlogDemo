package com.xks.binderdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    private TextView infoShowTv;

    private BookManager bookManager;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = BookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bookManager = null;
        }
    };

    private Book[] books = {
            new Book(1, "Java编程思想"),
            new Book(2, "第一行代码"),
            new Book(3, "Anroid开发艺术探索")
    };

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        infoShowTv = (TextView) findViewById(R.id.info_show_tv);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, BookService.class), connection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    /**
     * 添加书籍
     *
     * @param view
     */
    public void addBook(View view) {

        if (bookManager != null)
            try {
                bookManager.addBook(books[index++ % books.length]);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

    }

    public void printBook(View view) {
        if (bookManager != null)
            try {
                infoShowTv.setText(bookManager.printBook(books[index % books.length]));
            } catch (RemoteException e) {
                e.printStackTrace();
            }

    }

    public void getSize(View view) {
        if (bookManager != null)
            try {
                infoShowTv.setText(String.valueOf(bookManager.getBookSize()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }

    }

    public void getFirst(View view) {
        if (bookManager != null)
            try {
                infoShowTv.setText(bookManager.printBook(bookManager.get(0)));
            } catch (RemoteException e) {
                e.printStackTrace();
            }

    }
}
