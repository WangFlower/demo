package com.example.momo.myapplication.kot.demo

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.*

/**
 * create time 2018/11/12
 * by momo
 */
class ContinuationWapper<T>(val c: Continuation<T>, override val context: CoroutineContext = c.context) : Continuation<T> {


    var resumed = AtomicBoolean(false)


    override fun resumeWith(result: Result<T>) {
        if (resumed.compareAndSet(false, true)) {
            c.resumeWith(result)
        }
    }

    fun test(){

    }


}

suspend inline fun <T> suspendCoroutineW(crossinline block: (ContinuationWapper<T>) -> Unit): T =
        suspendCoroutine { c ->
            val wd = ContinuationWapper(c)
            block(wd)
        }
