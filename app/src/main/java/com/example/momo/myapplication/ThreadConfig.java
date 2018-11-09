package com.example.momo.myapplication;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create time 2018/11/9
 * by momo
 */
public class ThreadConfig {

    private static int CORE = 10;
    private static int MAX_ = 10;
    private static final long DEFAULT_KEEP_ALIVE_TIME = 60L;

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;


    public static ThreadConfig getInstance(){
        return Holder.instance;
    }

    private static class Holder {
        private static final ThreadConfig instance = new ThreadConfig();
    }



    private ThreadConfig() {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(CORE,
                new SamThreadFactory(),
                new RejectedHandler());
        scheduledThreadPoolExecutor.setMaximumPoolSize(CORE);
        scheduledThreadPoolExecutor.setKeepAliveTime(DEFAULT_KEEP_ALIVE_TIME * 2, TimeUnit.SECONDS);
    }

    public ExecutorService get(){
        return scheduledThreadPoolExecutor;
    }


    private class SamThreadFactory implements ThreadFactory {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            String threadName = mCount.getAndIncrement() + " #";
            Log.i("wangrenguang","newThread "+threadName);
            return new SamThread(r, threadName);
        }
    }

    private class SamThread extends Thread {
        public SamThread(Runnable tag, String name) {
            super(tag, name);
        }
    }

    private class RejectedHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            Log.e("wangrenguang", "Task %s rejected from %s"+r+executor);
        }
    }
}
