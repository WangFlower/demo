package com.example.momo.myapplication

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher

/**
 * create time 2018/11/9
 * by momo
 */
object SDispatchers {


    private class BG {
        val dispatcher: CoroutineDispatcher
            get() {
                return ThreadConfig.getInstance().get().asCoroutineDispatcher()
            }

    }

    val bg = BG().dispatcher
}
