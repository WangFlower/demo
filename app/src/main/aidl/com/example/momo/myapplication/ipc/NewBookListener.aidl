// NewBookListener.aidl
package com.example.momo.myapplication.ipc;
import com.example.momo.myapplication.ipc.Book;

// Declare any non-default types here with import statements

interface NewBookListener {
    void onNewBookarroved(in Book book);
}
