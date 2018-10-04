package com.example.lib.designmode.factory.simple;

/**
 * 简单工厂模式:简单工厂模式又叫静态方法模式（因为工厂类定义了一个静态方法）
 *
 * 优点：将创建实例的工作与使用实例的工作分开，使用者不必关心类对象如何创建，实现了解耦；
 * 把初始化实例时的工作放到工厂里进行，使代码更容易维护。 更符合面向对象的原则 & 面向接口编程，而不是面向实现编程。
 *
 * 缺点：工厂类集中了所有实例（产品）的创建逻辑，一旦这个工厂不能正常工作，整个系统都会受到影响；
 * 违背“开放 - 关闭原则”，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂。
 * 简单工厂模式由于使用了静态工厂方法，静态方法不能被继承和重写，会造成工厂角色无法形成基于继承的等级结构。
 *
 *
 */
public class SimpleFactory {


    public static Product getProduct(String name){

        switch (name){
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            case "C":
                return new ProductB();
        }
        return null;
    }

}
