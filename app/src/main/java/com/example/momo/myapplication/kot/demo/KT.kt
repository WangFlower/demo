package com.example.momo.myapplication.kot.demo

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * create time 2018/11/8
 * by momo
 */
class KT {


    fun test() = GlobalScope.async(Dispatchers.Main) {
        Log.i("wangrenguang", "KT----1---" + Thread.currentThread().name)
        "what"
    }

}