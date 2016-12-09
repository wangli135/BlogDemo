// IMyAidlInterface.aidl
package com.xks.binderdemo;

// Declare any non-default types here with import statements

import com.xks.binderdemo.Book;

interface BookManager {

    /**
     * 打印书籍
     */
    String printBook(in Book book);

    /**
     * 添加书籍
     */
    void addBook(in Book book);

    /**
     *得到书籍数量
     */
    int getBookSize();

    /**
     * 根据索引取书
     */
    Book get(int index);

}
