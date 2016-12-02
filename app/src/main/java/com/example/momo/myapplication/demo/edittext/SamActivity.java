package com.example.momo.myapplication.demo.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.momo.myapplication.R;

/**
 * Created by MOMO on 16/10/31.
 */

public class SamActivity extends Activity {

    boolean flag;
    Thread thread;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            thread.interrupt();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sam);



        thread = new Thread(){

            @Override
            public void run() {
                sam1.start();
                Log.i("wangrenguang","thread");
            }
        };
        thread.start();
        handler.sendEmptyMessageDelayed(0,5000);
    }


    Thread sam1 = new Thread(){
        int i = 1;
        @Override
        public void run() {
            for (;;){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("wangrenguang","sam1:"+(i++));
            }

        }
    };
}
