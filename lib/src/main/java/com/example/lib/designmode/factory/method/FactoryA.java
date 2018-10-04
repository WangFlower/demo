package com.example.lib.designmode.factory.method;

import com.example.lib.designmode.factory.simple.Product;
import com.example.lib.designmode.factory.simple.ProductA;

public class FactoryA extends FactoryMethod {
    @Override
    public Product manufacture() {
        return new ProductA();
    }
}
