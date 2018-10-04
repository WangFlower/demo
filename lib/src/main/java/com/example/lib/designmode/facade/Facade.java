package com.example.lib.designmode.facade;

public class Facade {

    SystemA systemA;
    SystemB systemB;
    SystemC systemC;

    public Facade(SystemA systemA, SystemB systemB, SystemC systemC) {
        this.systemA = systemA;
        this.systemB = systemB;
        this.systemC = systemC;
    }

    public void open(){
        systemA.open();
        systemB.open();
        systemC.open();
    }

    public void close(){
        systemA.close();
        systemB.close();
        systemC.close();
    }
}
