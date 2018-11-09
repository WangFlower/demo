package com.example.momo.myapplication.kot;

import android.app.Activity;


import java.util.List;

public class Main {



    public static void main(String[] s){
//        Demo demo = new Demo();
//        demo.test7();
//        Person person = new Person("ss",true);
//        person.getName();


        Demo2 demo2 = new Demo2();
        demo2.mian9();
    }


    private void fun(List<? super Activity> list1,List<? extends Activity> list2){
        list1.add(new Activity());

    }
}

