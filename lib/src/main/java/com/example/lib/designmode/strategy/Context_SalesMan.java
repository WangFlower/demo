package com.example.lib.designmode.strategy;

public class Context_SalesMan {

    private Strategy strategy;

    public Context_SalesMan(Strategy strategy){
        this.strategy = strategy;
    }

    public String getData(){
        return strategy.getData();
    }
}
