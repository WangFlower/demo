package com.example.momo.myapplication.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang.renguang on 2018/4/20.
 */

public class GenericDemo {





    public interface Generic1<T>{
        T call(T s);
    }

    public interface Generic2<T,S>{
        S call(T s);
    }

    public <T extends Generic1<T>> T get(T t){
        return t;
    }


    public class Son<T> implements Generic1<T>{

        @Override
        public T call(T s) {
            return null;
        }
    }


    public class Son2<T,S,D extends Object> implements Generic2<T,S>{

        @Override
        public S call(T s) {
            return null;
        }

        public D c(){
            return null;
        }

        <F> F d(T s){
            return null;
        }

        public <T> T k(){
            return null;
        }


    }


    public void getList(Generic1<? super Object> data){

    }


    public void main(){

    }


}
