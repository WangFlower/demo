package com.example.momo.myapplication.kot

data class SingtonUser(val a:String){


    fun fuck(runnable: Runnable){
        println("ssssss")
    }


    companion object : Runnable{
        override fun run() {
            println("run")
        }
        }

        fun bar(){
            println("ssss")
        }

        fun createUser(a:String) = SingtonUser(a)







}