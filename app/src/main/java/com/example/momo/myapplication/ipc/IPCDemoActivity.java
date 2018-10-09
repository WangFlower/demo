package com.example.momo.myapplication.ipc;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;

public class IPCDemoActivity extends Activity{


    private NewBookListener listener = new NewBookListener.Stub() {
        @Override
        public void onNewBookarroved(Book book) throws RemoteException {

        }
    };


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManage iBookManage = IBookManage.Stub.asInterface(service);

            try {
                // TOOD 需要在子线程访问
                List<Book> list = iBookManage.getBookList();
                iBookManage.addNewBookListener(listener);
            } catch (RemoteException e) {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(this,BookService.class);
//        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
