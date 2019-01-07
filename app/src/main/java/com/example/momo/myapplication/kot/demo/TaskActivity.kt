package com.example.momo.myapplication.kot.demo

import android.app.Activity
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.momo.myapplication.R
import com.example.momo.myapplication.SDispatchers
import com.example.momo.myapplication.kot.demo.UserTask.getUserInfo
import com.example.momo.myapplication.kot.demo.UserTask.loginOut
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine
import kotlinx.android.synthetic.main.task.*

/**
 * create time 2018/11/8
 * by momo
 */
class TaskActivity : Activity() {

    var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task)
        sam.text = "hahahha"
    }

    fun a() {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        GlobalScope.async(SDispatchers.bg + handler) {
            Log.i("wangrenguang", "----1---")
            for (i in 1..10) {
                Log.i("wangrenguang", "---2----")
                val userInfo = fuck()
                userInfo.await()
                Log.i("wangrenguang", "---3----")
            }
        }

    }


    fun aa() = GlobalScope.async(SDispatchers.bg + job) {
        Log.i("wangrenguang", "----aa---")
        fuck()
//        arrayOf(1, 2, 2, 4).map {
//            fuck()
//        }.forEach {
//            Log.i("wangrenguang", "----forEach---")
//            it.await()
//        }
    }


    //haha

    fun login() = GlobalScope.async(Dispatchers.Main + job) {
        Log.i("wangrenguang", "---login----")
        val loginout = loginOut().await()


    }


    fun fuck() = GlobalScope.async(SDispatchers.bg) {
        //        Log.i("wangrenguang", "---getUserInfo--1--")
////        getUserInfo().await()
////        Log.i("wangrenguang", "---getUserInfo--2--")
        Log.i("wangrenguang", "----fuck---")
        throw AssertionError()
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
                    Log.i("wangrenguang", "----2")
                    it.resume("")
                }

                override fun callBack(result: String?) {
//                    Log.i("wangrenguang","----3 "+it.isCancelled)
                    Log.i("wangrenguang", "----3 " + isActive)
                    it.resume(result)
                }
            })
//            Log.i("wangrenguang","----isCancelled:"+it.isCancelled)
            Log.i("wangrenguang", "----4" + isActive)
//            it.resume("")
        }

    }

    override fun onDestroy() {
        super.onDestroy()

    }


}