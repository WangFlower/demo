package com.example.momo.myapplication.kot;

import android.app.Activity;
import android.content.Context;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import kotlin.Unit;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.experimental.CancellableContinuation;
import kotlinx.coroutines.experimental.CoroutineDispatcher;
import kotlinx.coroutines.experimental.DisposableHandle;

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

