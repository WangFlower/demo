package com.example.momo.myapplication.kot.demo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.momo.myapplication.R
import com.example.momo.myapplication.SDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * create time 2018/11/8
 * by momo
 */
class TaskActivity : Activity() {

    lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task)
        textView = findViewById(R.id.textview)
        login()
    }

    fun login() = GlobalScope.async {
        val userInfo = getUserInfo()
        val friend = getFriend()

        userInfo.await()

        friend.await()

        val loginout = loginOut2().await()
        textView.text = loginout

    }


    fun getUserInfo() = GlobalScope.async(SDispatchers.bg) {
        UserTask.getUserInfo()
    }


    fun getFriend() = GlobalScope.async(SDispatchers.bg) {
        UserTask.getFriend()
    }

//    suspend fun loginOut() = GlobalScope.async {
//        suspendCoroutine<String> {
//            UserTask.loginOut { result -> it.resume(result) }
//        }
//    }


    suspend fun loginOut2() = GlobalScope.async {
        suspendCoroutine<String?> {
            UserTask.loginOut(false,object :UserTask.Callback{
                override fun success() {
                    it.resume("")
                }

                override fun callBack(result: String?) {
                    it.resume(result)
                }
            })
        }
    }


}