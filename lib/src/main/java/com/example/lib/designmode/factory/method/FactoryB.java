package com.example.lib.designmode.factory.method;

import com.example.lib.designmode.factory.simple.Product;
import com.example.lib.designmode.factory.simple.ProductA;
import com.example.lib.designmode.factory.simple.ProductB;

public class FactoryB extends FactoryMethod {
    @Override
    public Product manufacture() {
        return new ProductB();
    }
}
