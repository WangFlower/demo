package com.example.lib.plan.year_2018.mothy_10.day_19;


/**
 * synchronized  加锁方式
 */
public class Test2_1 {


    private int count;
    private final Object object = new Object();


    public Test2_1() {

    }

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
                synchronized (object) {
                    while (count >= 10) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("ProductThread:" + count);
                    object.notifyAll();
                }

            }
        }
    }


    public class TakeThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    while (count <= 0) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("TakeThread:" + count);
                    object.notifyAll();
                }
            }
        }
    }


    public static void main(String[] a) {
        Test2_1 t = new Test2_1();
        t.start();
    }


}
