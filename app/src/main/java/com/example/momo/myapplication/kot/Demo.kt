package com.example.momo.myapplication.kot

import android.view.View
import android.widget.Button
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation
import java.io.BufferedReader
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.AbstractCollection

class Demo {

    fun test(s: Array<String>, desc: String): Boolean {
        s.forEach {
            println(s)
            if (desc.equals(s)) {
                return true
            }
        }
        return false
    }

    fun test2(a: Int, b: Int): Int = if (a > b) a else b

    fun test3() {

        val a: Int
        a = 1;
        val aa: Int = 1


        var b = 2


        val s: String
        if (test2(1, 3) > 2) {
            s = "ssss"
        } else {
            s = "sss";
        }


        var answer = 42


        var p = Person("s", false)
        val r = Rectandle(10, 10);
        r.isSquare

        var random = Random()


    }


    enum class Color(val r: Int, val g: Int, val b: Int) {
        RED(250, 0, 0),
        WHITE(0, 0, 0),
        AAA(255, 255, 255)
    }


    fun test4(color: Color) =
            when (color) {
                Color.RED -> "dddd"
                Color.AAA -> "aaaa"
                else -> "ssss"
            }

    fun setOf(c1: Color, c2: Color) {
        println("setOf")
    }

    fun mix(c1: Color, c2: Color) =
            when (setOf(c1, c2)) {
                setOf(Color.WHITE, Color.AAA) -> {
                    println("mix  1")
                    "ssss"
                }
                else -> {
                    println("mix  2")
                    "ssss"
                }
            }


    fun test5() {
        val oneToTen = 1..10

        for (i in 100 downTo 1 step 2) {
            println(fizzBuzz(i))
        }


        val binaryReps = TreeMap<Char, String>()
        for (c in 'A'..'F') {
            val binary = Integer.toBinaryString(c.toInt())
            binaryReps[c] = binary
        }

        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")
        }

        val set = setOf(1, 7, 53)

        val list = listOf(1, 7, 53)

        val map = mapOf<Int, String>(1 to "one", 7 to "senver", 9 to "dd")


        list.last()

    }

    fun fizzBuzz(i: Int) = when {
        i % 15 == 0 -> "ddd"
        else -> "$i"
    }

    fun isLetter(c: Char) = c in 'a'..'z' || c !in 'A'..'z'

    fun readNumber(read: BufferedReader): Int? {
        try {
            val line = read.readLine()
            return Integer.parseInt(line)
        } catch (e: NumberFormatException) {
            return null
        } finally {
            read.close()
        }

        test6(SingtonUser)

    }

    fun <T> jojnToString(collection: Collection<T>,
                         separator: String = "",
                         prefix: String = "",
                         postFix: String = ""): String {
        val result = StringBuffer(prefix)
        for ((index, value) in collection.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(value)
        }
        result.append(postFix)
        return result.toString()
    }

    val aaa = jojnToString(listOf(1,2,3),postFix = "sss")
    //
    val aa = jojnToString(listOf(1,2,3),postFix = "",separator = "",prefix = "")

    val ss = listOf<Int>(1,3,3).jojnToString()

    val sss = "sss".lastChar()


    val strings :List<String> = listOf("sss","ssss")
    val a1 = strings.last()


    fun test6(args:Array<String>){
        // 展开操作符
        val lsit = listOf<String>("args",*args)
    }

    // 1  to ""  中缀调用
    val amap = mapOf<Int,String>(1.to("dddd"),1 to "eee")


    val s = "s" cao "s"

    val ssss = "s".cao("s")

    fun test6(run:Runnable){
        val user = User("d",2)
        user.saveUser(user)

    }


    fun test7(){

        val age = User::age

        println("age= $age")


    }











}