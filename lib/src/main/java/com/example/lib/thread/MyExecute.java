package com.example.lib.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class MyExecute extends ThreadPoolExecutor{

    private MyThreadFactory threadFactory = new MyThreadFactory();

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final AtomicLong totalTime = new AtomicLong();
    private final AtomicLong numTask = new AtomicLong();

    public MyExecute(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i, i1, l, timeUnit, blockingQueue);
        setThreadFactory(threadFactory);
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        // println
        startTime.set(System.nanoTime());
    }


    @Override
    protected void afterExecute(Runnable runnable, Throwable throwable) {
        long endTime = System.nanoTime();
        long taskTime = endTime- startTime.get();
        numTask.incrementAndGet();
        totalTime.addAndGet(taskTime);
        super.afterExecute(runnable, throwable);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return super.newTaskFor(callable);
    }

    @Override
    protected void terminated() {
        super.terminated();
    }




    public class MyThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable runnable) {
            return new MyThread("wang",runnable);
        }
    }



}
