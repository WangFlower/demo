package com.example.lib.thread;

import java.util.concurrent.atomic.AtomicInteger;


public class MyThread extends Thread {

    private static final AtomicInteger created = new AtomicInteger();

    private static final AtomicInteger alive = new AtomicInteger();


    public MyThread(String tag, Runnable r) {
        super(r, tag);
        created.incrementAndGet();
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                // println  处理异常
            }
        });
    }

    @Override
    public void run() {
        // println
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
        }

    }
}
