package com.example.lib.designmode.adapter;


/**
 * 对象的适配器模式
 */
public class Adapter220V2 implements Target{

    private PowerPort220V powerPort220V;
    public Adapter220V2(PowerPort220V powerPort220V){
        this.powerPort220V = powerPort220V;
    }

    @Override
    public void Convert_110v() {
        powerPort220V.Output_220v();
    }
}
