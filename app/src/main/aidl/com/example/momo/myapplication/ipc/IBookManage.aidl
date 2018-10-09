// IBookManage.aidl
package com.example.momo.myapplication.ipc;

import com.example.momo.myapplication.ipc.Book;
import com.example.momo.myapplication.ipc.NewBookListener;
// Declare any non-default types here with import statements

interface IBookManage {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addBook(in Book book);

    List<Book> getBookList();

    void addNewBookListener(NewBookListener newBookListener);

    void removeNewBookListener(NewBookListener newBookListener);
}
