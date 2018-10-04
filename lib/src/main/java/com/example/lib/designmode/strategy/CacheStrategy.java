package com.example.lib.designmode.strategy;

public class CacheStrategy implements Strategy{


    @Override
    public String getData() {
        System.out.println("CacheStrategy getData");
        return "CacheStrategy";
    }
}
