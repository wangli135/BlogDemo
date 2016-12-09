package com.xks.binderdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Xingfeng on 2016-12-09.
 */

public class Book implements Parcelable {

    private int isbn;
    private String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.isbn);
        dest.writeString(this.name);
    }

    public Book() {
    }

    public Book(int isbn, String name) {
        this.isbn = isbn;
        this.name = name;
    }

    protected Book(Parcel in) {
        this.isbn = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", name='" + name + '\'' +
                '}';
    }
}
