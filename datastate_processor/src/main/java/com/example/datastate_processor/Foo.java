package com.example.datastate_processor;   // PackageElement

/**
 * @author wang.renguang
 * @time 2019/1/23
 */
public class Foo {   // TypeElement

    @State
    private int a;      // VariableElement
    private Foo other;  // VariableElement

    public Foo() {
    }    // ExecuteableElement

    public void setA(  // ExecuteableElement
                       int newA   // TypeElement
    ) {

    }
}
