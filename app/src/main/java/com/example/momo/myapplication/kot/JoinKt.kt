package com.example.momo.myapplication.kot

import java.lang.StringBuilder


val UNN = "sss"
const val UN = "sss"

fun <T> Collection<T>.jojnToString(
        separator: String = "",
        prefix: String = "",
        postFix: String = ""): String =
//    val result = StringBuffer(postFixrefix)
    this.foldIndexed(StringBuilder(prefix)){index, acc,item->
        acc.append(item)
    }.toString()
//    for ((index, value) in this.withIndex()) {
//        if (index > 0) result.append(separator)
//        result.append(value)
//    }
//    result.append(postFix)
//    return result.toString()
//}


fun String.lastChar(): Char = this.get(this.length - 1)


var StringBuffer.lastChat: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

// 中缀关键字infix
infix fun <A,B> A.cao(that:B) :Pair<A,B> = Pair(this,that)

// 中缀关键字infix
public infix fun <A,B> A.mmm(that:B) :Pair<A,B> = Pair(this,that)

infix fun Any.tooo(that: Any)= Pair(this,that)

fun Any?.toString():String{
    if(this==null) return "null"
    return this.toString()
}

