package com.example.momo.myapplication.kot

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * create time 2018/11/7
 * by momo
 */
class Student {
    var name: String by Delegate()
}

class Student2 {
//        var name:String
    fun demo() {
        val s = Student().apply {
            name = "Mikyou"
        }
    }
}


class Delegate {
    operator fun getValue(student: Student, property: KProperty<*>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    operator fun setValue(student: Student, property: KProperty<*>, s: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}