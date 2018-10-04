package com.example.lib.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskDemo {


    private final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            System.out.println("call----1");
            Thread.sleep(10000);
            System.out.println("call----2");
            return "wangrenguang";
        }
    });

    private final Thread thread = new Thread(futureTask);

    private void start(){
        thread.start();
    }

    public String get(long var1, TimeUnit var3) throws ExecutionException, InterruptedException, TimeoutException {
        return futureTask.get(var1,var3);
    }

    public static void main(String[] a){
        FutureTaskDemo futureTaskDemo = new FutureTaskDemo();
        System.out.println("start before");
        futureTaskDemo.start();
        System.out.println("start after");

        try {
            System.out.println(futureTaskDemo.get(5, TimeUnit.SECONDS));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
