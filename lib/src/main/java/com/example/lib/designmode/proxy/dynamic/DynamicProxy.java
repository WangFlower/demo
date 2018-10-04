package com.example.lib.designmode.proxy.dynamic;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy<P> implements InvocationHandler {

    private P proxy;


    public P newProxyInstance(P proxy) {
        this.proxy = proxy;
        return (P) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), proxy.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("invoke before");
        Object result = method.invoke(proxy,objects);
        System.out.println("invoke after");
        return result;
    }
}
