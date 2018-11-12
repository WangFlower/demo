package com.example.momo.myapplication.kot.demo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.momo.myapplication.R
import com.example.momo.myapplication.SDispatchers
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

/**
 * create time 2018/11/8
 * by momo
 */
class TaskActivity : Activity() {

    lateinit var textView: TextView
    var job: Job = Job()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task)
        textView = findViewById(R.id.textview)
        job = login()

    }

    fun login() = GlobalScope.async(Dispatchers.Main+job) {
        val loginout = loginOut().await()
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

    suspend fun loginOut() = GlobalScope.async(SDispatchers.bg) {
        suspendCoroutineW<String?> {
            UserTask.loginOut(false, object : UserTask.Callback {
                override fun success() {
                    it.resumeWith(Result.success("success"))
                }

                override fun callBack(result: String?) {
                    it.test()
                    it.resumeWith(Result.success(result))
                }
            })
            it.resumeWith(Result.success("222"))
        }

    }


    suspend fun loginOut2() = GlobalScope.async(SDispatchers.bg) {
        suspendCoroutine<String?> {


            UserTask.loginOut(false, object : UserTask.Callback {
                override fun success() {
                    Log.i("wangrenguang","----2")
                    it.resume("")
                }

                override fun callBack(result: String?) {
//                    Log.i("wangrenguang","----3 "+it.isCancelled)
                    Log.i("wangrenguang","----3 "+isActive)
                    it.resume(result)
                }
            })
//            Log.i("wangrenguang","----isCancelled:"+it.isCancelled)
            Log.i("wangrenguang","----4"+isActive)
//            it.resume("")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()

    }


}