package com.example.lib.plan.year_2018.mothy_10.day_19

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class TestKt{

    val lock = ReentrantLock()
    val notFull = lock.newCondition()
    val notEmpty = lock.newCondition()

    var count = 0

    fun start(){

    }


    class ProductThread : Runnable{
        override fun run() {
            while (true){
            }
        }
    }
}

