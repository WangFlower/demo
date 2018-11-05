package com.example.momo.myapplication.kot

abstract class View(var height:Int) : Clickable ,Fousable {



    override fun showOff() {
        super<Fousable>.showOff()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    open fun updateHeight(h: Int) {
        height = h
    }


    abstract fun animate()

}
