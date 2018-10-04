package com.example.lib.designmode.adapter;


/**
 * 适配器模式：定义一个包装类，用于包装不兼容接口的对象
 *
 * 主要作用:把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。
 *
 * 解决的问题:原本由于接口不兼容而不能一起工作的那些类可以在一起工作
 */
public class Adapter220V extends PowerPort220V implements Target{
    //期待的插头要求调用Convert_110v()，但原有插头没有
    //因此适配器补充上这个方法名
    //但实际上Convert_110v()只是调用原有插头的Output_220v()方法的内容
    //所以适配器只是将Output_220v()作了一层封装，封装成Target可以调用的Convert_110v()而已

    @Override
    public void Convert_110v() {
        this.Output_220v();
    }
}
