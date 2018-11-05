package com.example.momo.myapplication.kot

import java.lang.IllegalArgumentException

class Demo2 {


    interface Expr

    class Num(val value: Int) : Expr


    class Sum(val left: Expr, val right: Expr) : Expr


    fun eval(e: Expr): Int =
            when (e) {
                is Num -> e.value
                is Sum -> eval(e.right) + eval(e.left)
                else -> throw IllegalArgumentException("UN")
            }


}