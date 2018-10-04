package com.example.lib.designmode.proxy;

/**
 * 代理模式
 */
public class StaticProxyDemo {


    interface Student{
        void buyMac();
    }


    static class HuangNiu implements Student{

        @Override
        public void buyMac() {
            System.out.print("buy mac");
        }
    }


    static class Proxy implements Student{

        @Override
        public void buyMac() {
            HuangNiu huangNiu = new HuangNiu();
            huangNiu.buyMac();
            System.out.print("重新再包装下");
        }
    }

    public static void main(String[] args){
        Student proxy = new Proxy();
        proxy.buyMac();
    }

}
