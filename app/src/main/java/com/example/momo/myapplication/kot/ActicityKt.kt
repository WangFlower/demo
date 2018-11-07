package com.example.momo.myapplication.kot

import android.app.Activity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.LinearLayout
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class ActicityKt : Activity(), CoroutineScope {


    lateinit var job: Job


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job  //To change initializer of created properties use File | Settings | File Templates.


    fun doSomething() {
        repeat(10) { i ->
            launch {
                delay((i + 1) * 200L) // 延迟200毫秒、400毫秒、600毫秒等等不同的时间
                println("Coroutine $i is done")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        val view = LinearLayout(this)

        view.setOnClickListener {
            println("onClick")
        }


        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        val people = listOf(User("ss", 12), User("ss", 13))

        println(people.maxBy { it.age })

        val age = User::age



        println(people.minBy(User::age))


        people.maxBy({ it.age })


        /**
         * x: Int, y: Int  参数
         *
         *  x + y 函数主体
         */
        val sum = { x: Int, y: Int -> x + y }

        println(sum(1, 2))

        kotlin.run { }

        run { println("") }


        val age2 = User("ss", 21).age


        view.post { print("sss") }


        val user = User("", 22)
//        val (name,age) = User

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    // 延迟加载  第一次访问后会缓存值
    val lazyValue: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        println("")
        "hello"
    }


    fun message(message: Collection<String>, prefix: String) {
        message.forEach {
            print("$prefix $it")
        }

    }

    /**
     * with --->build
     */
    fun alphabet(): String {

        val x = demo()

        val stringBuffer = StringBuffer()
        return with(stringBuffer) {
            for (letter in 'A'..'z') {
                this.append(letter)
            }
            append("end")
            this.toString()
        }
    }

    fun demo(): Int? {
        fooo {
            println("")
        }
        return null
    }

    fun demo2(st: Source<String>) {
        val obs: Source<Any> = st
    }


    interface Source<out T> {
        fun nextT(): T
    }


    // 重载操作符demo
    data class Point(val x: Int, val y: Int)

    operator fun Point.unaryMinus() = Point(-x, -y)
    val point = Point(-1, -1)
    val p = -point


    fun foo(qux: () -> Int) {
        qux
    }

    fun <T> fooo(init: T) {

    }

    val eps = 1E-10 // "good enough", could be 10^-15

    fun findFixPoint(x: Double = 1.0): Double = if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))

}