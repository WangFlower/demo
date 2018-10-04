package com.example.lib.designmode.strategy;

public class NetStrategy implements Strategy{


    @Override
    public String getData() {
        System.out.println("NetStrategy getData");
        return "NetStrategy";
    }
}
