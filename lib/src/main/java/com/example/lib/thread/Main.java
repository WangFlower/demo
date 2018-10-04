package com.example.lib.thread;

import java.security.PrivilegedAction;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] a){
        ExecutorService e = Executors.newCachedThreadPool();
        e.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        Future future1 = e.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        Future<String> future2 = e.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
//        PrivilegedAction
    }
}
