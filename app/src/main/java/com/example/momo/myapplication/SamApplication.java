package com.example.momo.myapplication;

import android.app.Application;

/**
 * Created by MOMO on 17/1/11.
 */

public class SamApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        BlockCanary.install(this, new MOMOBlockCanaryContext()).start();
        LooperMonitorHelper.start();
    }
}
