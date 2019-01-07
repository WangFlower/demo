package com.example.momo.myapplication.dragger.demo1;

import dagger.Component;

/**
 * @author wang.renguang
 * @time 2019/1/4
 * <p>
 * <p>
 * 一般用于标记接口；持有某个类其中含有 @Inject 的变量
 * 会通过@Component 中的modules 中去查找对于的实例对象赋值给 @Inject 的变量
 */
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(Main main);

    void a(Main main);
}
