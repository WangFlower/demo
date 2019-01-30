package com.example.lib.designmode.facade;


/**
 * 外观模式：
 * 定义了一个高层、统一的接口，外部与通过这个统一的接口对子系统中的一群接口进行访问
 *
 * 主要作用：
 * 实现客户类与子系统类的松耦合
 * 降低原有系统的复杂度
 * 提高了客户端使用的便捷性，使得客户端无须关心子系统的工作细节，通过外观角色即可调用相关功能
 * 避免了系统与系统之间的高耦合度
 * 使得复杂的子系统用法变得简单
 *
 * 缺点：
 * 在不引入抽象外观类的情况下，增加新的子系统可能需要修改外观类或客户端的源代码，违背了“开闭原则”
 * 不能很好地限制客户使用子系统类，如果对客户访问子系统类做太多的限制则减少了可变性和灵活性。
 */
public class Demo {

    public void main(String[] a) {

        //在不使用外观模式的情况下，需要对每个系统都进行操作
        SystemA systemA = new SystemA();
        SystemB systemB = new SystemB();
        SystemC systemC = new SystemC();

        systemA.open();
        systemB.open();
        systemC.open();


        systemA.close();
        systemB.close();
        systemC.close();



        // 使用外观模式

        Facade facade = new Facade(new SystemA(),new SystemB(),new SystemC());
        facade.open();
        facade.close();
    }
}
