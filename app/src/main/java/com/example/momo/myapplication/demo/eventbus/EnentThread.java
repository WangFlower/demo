package com.example.momo.myapplication.demo.eventbus;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class EnentThread extends Thread{

    int i = 0;

    @Override
    public void run() {
        while (true){

            if(i>100000){
                i=0;
            }
            i++;
            EventBus.getDefault().post(new MessageEvent(""+i));
            Log.i("wangrenguang2","run "+i);
        }
    }
}
