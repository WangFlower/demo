package com.example.momo.myapplication;

import android.app.Application;

import com.example.momo.myapplication.demo.eventbus.EnentThread;
import com.github.moduth.blockcanary.BlockCanary;

/**
 * Created by MOMO on 17/1/11.
 */

public class SamApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.setContext(this);
        BlockCanary.install(this, new MOMOBlockCanaryContext()).start();
        LooperMonitorHelper.start();
//        ActivityThreadHookHelper.doHookActivityStart();
//        new EnentThread().start();
    }
}
