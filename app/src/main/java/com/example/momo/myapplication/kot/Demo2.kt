package com.example.momo.myapplication.kot

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.ticker
import java.io.IOException
import java.lang.IllegalArgumentException
import kotlin.system.measureTimeMillis


class Demo2 {


    interface Expr

    class Num(val value: Int) : Expr


    class Sum(val left: Expr, val right: Expr) : Expr


    fun eval(e: Expr): Int =
            when (e) {
                is Num -> e.value
                is Sum -> eval(e.right) + eval(e.left)
                else -> throw IllegalArgumentException("UN")
            }


    fun <T, R> Collection<T>.fold(inital: R, combine: (acc: R, nextElement: T) -> R): R {
        var accumulator: R = inital
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }

    fun main() {
        val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
        val twoParameters: (String, Int) -> String = repeatFun // OK

        fun runTransformation(f: (String, Int) -> String): String {
            return f("hello", 3)
        }

        val result = runTransformation(repeatFun) // OK
        println("result = $result")
    }


    fun fail(message: String) {

        GlobalScope.launch {
            // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主线程中的代码会立runBlocking即执行
        runBlocking {
            // 但是这个函数阻塞了主线程
            delay(2000L)  // ...我们延迟2秒来保证JVM的存活
        }
    }


    fun post() {
        GlobalScope.launch {
            getUser()

        }

    }


    suspend fun getInfo() {
        getUser()
    }


    suspend fun getUser() {
        delay(1000)
    }


    suspend fun channelFun() {
        val channel = Channel<Int>()

        GlobalScope.launch {
            for (x in 1..5) channel.send(x)
            channel.close()
        }

        for (y in channel) println(y)
        println("Done!")
    }


    fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce {
        var x = 1
        while (true) send(x++)
    }

    fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
        for (x in numbers) send(x * x)
    }

    fun dofun() {

        runBlocking {
            val numbers = produceNumbers()
            val square = square(numbers)
            for (i in 1..5) println(square.receive())
            coroutineContext.cancelChildren()
        }
    }


    data class Ball(var hits: Int)

    fun main1() = runBlocking {

        val table = Channel<Ball>()
        GlobalScope.launch {
            delay(300)
            player("ping", table)
        }
        GlobalScope.launch {
            player("pong", table)
        }
        table.send(Ball(0))
        delay(1000)
        coroutineContext.cancelChildren()
    }

    suspend fun player(name: String, table: Channel<Ball>) {
        for (ball in table) {
            ball.hits++
            println("$name $ball")
            delay(300) // 等待一段时间
            table.send(ball) // 将球发送回去
        }
    }


    fun main2() = runBlocking {
        val tikerChannel = ticker(delayMillis = 100, initialDelayMillis = 0)
        var nextElement = withTimeoutOrNull(1) {
            tikerChannel.receive()
        }
        println("")
    }


    suspend fun doSomethingUsefulOne(): Int {
        println("doSomethingUsefulOne 1")
        delay(1000L)
        println("doSomethingUsefulOne 2")
        return 13
    }


    // somethingUsefulOneAsync 函数的返回值类型是 Deferred<Int>
    fun doSomethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }


    suspend fun doSomethingUsefulTwo(): Int {
        println("doSomethingUsefulTwo 1")
        delay(1000L)
        println("doSomethingUsefulTwo 2")
        return 13
    }

    fun main3() = runBlocking {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${two + one}")
    }

    fun main4() = runBlocking {

        GlobalScope.launch {
            doSomethingUsefulOne()

        }

        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${two.await() + one.await()}")
    }

    fun main5() = runBlocking {

        val job1 = GlobalScope.launch {
            doSomethingUsefulOne()
        }


        val job2 = GlobalScope.launch {
            doSomethingUsefulTwo()
        }

        delay(3000)
    }

    fun main6() = runBlocking {

        val one = async(start = CoroutineStart.LAZY) {
            doSomethingUsefulOne()
        }

        val two = async(start = CoroutineStart.LAZY) {
            doSomethingUsefulTwo()
        }
        one.start()
        two.start()

        println("The answer is ${one.await() + two.await()}")
    }

    suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        one.await() + two.await()
    }

    fun main7() = runBlocking {
        val time = measureTimeMillis {
            println("The answer is ${concurrentSum()}")
        }
        println("Completed in $time ms")

    }

    /**
     * 当一个子协程失败的时候 第一个协程会被取消
     */
    suspend fun fail(): Int = coroutineScope {
        val one = async(CoroutineName("v2coroutine")) {
            try {
                delay(Long.MAX_VALUE)
                42
            } finally {
                println("First child was cancelled")
            }

        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }

    fun main8() = runBlocking {
        try {
            fail()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    fun mian9() = runBlocking {
        println("-------- ${Thread.currentThread().name}")
        GlobalScope.launch {
            // 运行在父协程的上下文中，即 runBlocking 主协程
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        GlobalScope.launch(Dispatchers.Unconfined) {
            // 不受限的--将工作在主线程中
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        GlobalScope.launch(Dispatchers.Default) {
            // 将会获取默认调度器
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        GlobalScope.launch(newSingleThreadContext("MyOwnThread")) {
            // 将使它获得一个新的线程
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Unconfined + CoroutineName("test")) {
            println("")

        }

        coroutineContext[Job]
    }


    fun main10() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
        }
        val job = GlobalScope.launch(handler) {
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    throw ArithmeticException()
                }
            }
            launch {
                delay(100)
                throw IOException()
            }
            delay(Long.MAX_VALUE)
        }
        job.join()
    }

    fun main11() = runBlocking {

        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)) {
            val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
                println("First child is failing")
                throw AssertionError("First child is cancelled")
            }
        }

    }


}