package com.example.lib;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 线程取消demo
 */
public class CancelDemo {


    private final ExecutorService executorService;

    public CancelDemo(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void timedRun(Runnable r, long timeout, TimeUnit timeUnit){

        Future<?> task = executorService.submit(r);

        try {
            task.get(timeout,timeUnit);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            task.cancel(true);
        }
    }
}
