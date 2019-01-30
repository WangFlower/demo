package com.example.momo.myapplication.kot

/**
 * @author wang.renguang
 * @time 2019/1/23
 */
class SingInstance2 private constructor(val name: String) {


    companion object {
        val instance: SingInstance2 by lazy {
            SingInstance2("sss")
        }

        val temp: String by lazy {
            "s"
        }
    }

}
