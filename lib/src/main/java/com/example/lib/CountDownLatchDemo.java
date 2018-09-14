package com.example.lib;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public long timeTask(int threads, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threads);


        for (int i = 0; i < threads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("-----1------"+this);
                        startGate.await();
                        System.out.println("-----2------"+this);
                        try {
                            task.run();
                        } finally {
                            System.out.println("-----3------"+this);
                            endGate.countDown();
                            System.out.println("-----4------"+this);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }).start();
        }

        long start = System.nanoTime();
        System.out.println("-----5------"+this);
        startGate.countDown();
        System.out.println("-----6------"+this);
        endGate.await();
        System.out.println("-----7------"+this);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] a){
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        try {
            countDownLatchDemo.timeTask(5, new Runnable() {
                @Override
                public void run() {
                    System.out.println("-----run------");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
