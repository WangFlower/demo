package com.example.momo.myapplication.kot

import android.app.Activity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.LinearLayout

class ActicityKt : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        val age2 = User("ss",21).age

    }


    fun message(message: Collection<String>, prefix: String) {
        message.forEach {
            print("$prefix $it")
        }

    }
}