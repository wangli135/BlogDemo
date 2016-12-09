package com.xks.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {

    private List<Book> bookList = new ArrayList<>();

    public class BookBinder extends BookManager.Stub {
        @Override
        public String printBook(Book book) throws RemoteException {
            return book.toString();
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            bookList.add(book);
        }

        @Override
        public int getBookSize() throws RemoteException {
            return bookList.size();
        }

        @Override
        public Book get(int index) throws RemoteException {
            return bookList.get(index);
        }
    }

    private BookBinder bookBinder = new BookBinder();


    public BookService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bookBinder;
    }
}
