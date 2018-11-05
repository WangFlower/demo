package com.example.momo.myapplication.kot

class Delegating<T>(private val innerList: MutableCollection<T> = ArrayList<T>()) : MutableCollection<T> by innerList {

    private var objectsAdd = 0


    override fun add(element: T): Boolean {
        objectsAdd++
        return innerList.add(element)
    }


    override fun remove(element: T): Boolean {
        objectsAdd--
        return innerList.remove(element)
    }


}
