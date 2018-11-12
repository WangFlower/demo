package com.example.momo.myapplication.kot.demo;

import android.os.Handler;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

/**
 * create time 2018/11/8
 * by momo
 */
public class UserTask {

    public static boolean login(String name,String psw){
        return true;
    }


    public static UserInfo getUserInfo(){
        Log.i("wangrenguang","getUserInfo  1");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("wangrenguang","getUserInfo  2");
        return new UserInfo();
    }

    public static Friend getFriend() throws InterruptedException {
        Log.i("wangrenguang","getFriend  1");
        Thread.sleep(15000);
        Log.i("wangrenguang","getFriend  2");
        return new Friend();
    }

    public static void loginOut(boolean tag,Callback callback) throws InterruptedException {
//        Thread.sleep(15000);
        callback.callBack("loginOut");
    }


    public interface Callback{
        void callBack(@android.support.annotation.Nullable String result);
        void success();
    }
}
