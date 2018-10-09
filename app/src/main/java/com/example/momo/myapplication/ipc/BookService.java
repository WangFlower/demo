package com.example.momo.myapplication.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {

    private List<Book> books = new ArrayList<>();

    /**
     * 需要使用RemoteCallbackList
     */
    private RemoteCallbackList<NewBookListener> listenerRemoteCallbackList = new RemoteCallbackList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private Binder mBinder = new IBookManage.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return books;
        }

        @Override
        public void addNewBookListener(NewBookListener newBookListener) throws RemoteException {
            listenerRemoteCallbackList.register(newBookListener);
        }

        @Override
        public void removeNewBookListener(NewBookListener newBookListener) throws RemoteException {
            // 这里的newBookListener  和addNewBookListener  在多进程中 不会是同一个对象 需要使用listenerRemoteCallbackList 来维护
            listenerRemoteCallbackList.unregister(newBookListener);
        }
    };

    private void onNewBook(Book book){
        final int n = listenerRemoteCallbackList.beginBroadcast();
        for(int i=0;i<n;i++){
            NewBookListener newBookListener = listenerRemoteCallbackList.getBroadcastItem(i);
            try {
                // TODO 子线程中去调用  或者这个方法在客户端不耗时
                // onNewBookarroved运行在客户端binder线程池中  不能做更新UI的操作
                newBookListener.onNewBookarroved(book);
            } catch (RemoteException e) {
            }
        }
        listenerRemoteCallbackList.finishBroadcast();
    }
}
