package com.example.momo.myapplication.dragger.demo1;

import javax.inject.Inject;

/**
 * @author wang.renguang
 * @time 2019/1/4
 */
public class Main {

    @Inject
    Student student;

    @Inject
    Teacher teacher;


    public Main() {
        DaggerMainComponent.builder().build().a(this);

    }


    public static void main(String[] a) {
        Main main = new Main();
        System.out.println(main.student);
        System.out.println(main.teacher);
    }
}
