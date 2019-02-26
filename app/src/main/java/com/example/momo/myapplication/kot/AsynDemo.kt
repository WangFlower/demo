package com.example.momo.myapplication.kot

import java.util.*
import kotlin.concurrent.timerTask

/**
 * @author wang.renguang
 * @time 2019/2/18
 */

class AsynDemo {


    fun a() {
        Timer().schedule(timerTask {
            TODO("Do something")
        }, 2000)
    }
}