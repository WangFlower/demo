package com.example.momo.myapplication.kot

/**
 * 密封类不能定义外部继承者
 */
sealed class SealedDemo{


    class Num(val value:Int) : SealedDemo()



    fun a(sealedDemo: SealedDemo)= when(sealedDemo){
        is Num -> println("")
    }


}