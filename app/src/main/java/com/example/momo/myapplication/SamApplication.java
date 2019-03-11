package com.example.momo.myapplication;

import android.app.Application;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.momo.myapplication.monitor.BlockMonitor;
import com.github.moduth.blockcanary.BlockCanary;

/**
 * Created by MOMO on 17/1/11.
 */

public class SamApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.setContext(this);
        BlockCanary.install(this, new MOMOBlockCanaryContext()).start();
        LooperMonitorHelper.start();
//        ActivityThreadHookHelper.doHookActivityStart();
//        new EnentThread().start();
        BlockMonitor.getInstance().start();
    }
}
