package com.example.momo.myapplication.kot

class Button(height: Int) : View(height) {


    var width: Int
        get() {
            println("wangrenguang")
            return height
        }
        private set(value) {
            width = value
        }


    var fff: Int = 2
        private set

    override fun animate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    fun a() = super.updateHeight(111)


    final override fun updateHeight(h: Int) {
        super.updateHeight(h)
    }


    /**
     * 默认是静态内部类
     */
    class State constructor(_name: String) {

        val name = _name
        val fuck: String

        init {
            fuck = _name + "sss"
        }


        fun aaa() {

        }
    }

    /**
     * 内部类
     */
    inner class InnerState(val name: String, val age: Int = 12) {
        fun bbb() {
            this@Button.a()
        }
    }

    class Fuck private constructor()


    class Fuck2 {

        private constructor()
    }


    val state = InnerState("s")
    val state2 = InnerState("s", 2)
    val state3 = InnerState(age = 2, name = "ss")

    companion object


}