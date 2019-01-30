package com.example.lib.designmode.proxy.dynamic;

public class DynamicProxyDemo {

    public void main(String[] a) {


        MacHuangNiu macHuangNiu = new MacHuangNiu();

        Student proxy = (Student) new DynamicProxy<>().newProxyInstance(macHuangNiu);
        proxy.bugMac();

    }
}
