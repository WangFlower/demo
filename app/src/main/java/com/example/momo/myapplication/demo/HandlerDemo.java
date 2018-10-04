package com.example.momo.myapplication.demo;


import android.os.Handler;
import android.os.Looper;

public class HandlerDemo extends Thread{


    @Override
    public void run() {
        super.run();
        Looper.prepare();
        Handler handler = new Handler();

    }
}
