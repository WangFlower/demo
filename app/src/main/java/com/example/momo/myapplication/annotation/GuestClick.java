package com.example.momo.myapplication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wang.renguang on 2018/5/30.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface GuestClick {

}
