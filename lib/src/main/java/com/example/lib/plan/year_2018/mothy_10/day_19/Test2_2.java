package com.example.lib.plan.year_2018.mothy_10.day_19;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 方式  和ArrayBlockingQueue内部原理差不多
 */
public class Test2_2 {

    public final Lock lock = new ReentrantLock();
    public final Condition notFull = lock.newCondition();
    public final Condition notEmpty = lock.newCondition();
    public int count;


    public void start() {
        new Thread(new ProductThread()).start();
        new Thread(new ProductThread()).start();
        new Thread(new ProductThread()).start();
        new Thread(new TakeThread()).start();
        new Thread(new TakeThread()).start();
    }


    public class ProductThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count >= 10) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("ProductThread:" + count);
                    notEmpty.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    public class TakeThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (count <= 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("TakeThread:" + count);
                    notFull.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }



    public static void main(String[] a) {
        Test2_2 t = new Test2_2();
        t.start();
    }

}
