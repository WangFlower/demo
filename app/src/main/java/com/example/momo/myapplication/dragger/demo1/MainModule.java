package com.example.momo.myapplication.dragger.demo1;

import dagger.Module;
import dagger.Provides;

/**
 * @author wang.renguang
 * @time 2019/1/4
 * <p>
 * 如果Student 是一个第三方库中的类 我们是没办法去加注解的 这时候
 * 可以用@Module 注解拉提供student实例对象
 */
@Module
public class MainModule {


    /**
     * 提供实体的注解
     *
     * @return
     */
    @Provides
    public Student providerC() {
        return new Student();
    }
}
