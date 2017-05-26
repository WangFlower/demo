package com.example.momo.myapplication.rx;

import android.widget.ImageView;

/**
 * Created by sam on 2017/4/13.
 */

public class ImageLoader {

    public interface CallBack{
        void onCallBack();
    }




    public static void load(final CallBack callBack){

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(callBack!=null){
                    callBack.onCallBack();
                }
            }
        }.start();


    }



}
