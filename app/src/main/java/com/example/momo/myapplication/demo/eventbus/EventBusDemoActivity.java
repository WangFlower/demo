package com.example.momo.myapplication.demo.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.momo.myapplication.SamEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusDemoActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("wangrenguang","onCreate "+Thread.currentThread().getName());
        EventBus.getDefault().register(this);

    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(MessageEvent event){
        Log.i("wangrenguang","onMessageEvent:"+event.toString()+""+Thread.currentThread().getName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
