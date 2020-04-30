package com.example.kotlincoroutinelearning

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    //Learning resources
    // https://www.youtube.com/watch?v=ShNhJ3wMpvQ&list=PLQkwcJG4YTCQcFEPuYGuv54nYai_lwil_&index=1

    var txtMsg: TextView? = null

    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    var currentDate = sdf.format(Date())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMsg = findViewById<TextView>(R.id.txtmsg) as TextView


        val job=GlobalScope.launch(Dispatchers.Default) {

            currentDate = sdf.format(Date())

            Log.d(
                "TAG",
                "Before coroutind get delayed ${Thread.currentThread().name} ${currentDate}+ "
            )
            //To delay the execution of coroutine we can use the coroutine delay(millisecond)
            //function
            //delay(5000L)

            currentDate = sdf.format(Date())
            //Here we are printing the name of the thread in which it is executing
            Log.d("TAG", "Hello From Coroutine ${Thread.currentThread().name} ${currentDate}+ ")

            //video 3
            //coroutine1()
            //coroutine2()

            //video 4
            //settingText()

            //video 6
            Log.d("TAG","In Video 6 Before function")
            repeat(5){
                delay(1500)
                Log.d("TAG","In Video 6 join function")
            }
            Log.d("TAG","In Video 6 After function")
        }
        //video 6
        runBlocking {

            //To wait till the job coroutine complete its execution
            job.join()

            //Cancellation
            //delay(2000L)
            //job.cancel()
        }

        //Log.d("TAG","Hello From Main Thread App ${Thread.currentThread().name}")

        /*
        * Video 5 Use of runBlocking{} block.
        * It is used to run the operations synchronously and delay won't be added highest delay will be used.
        * If we run it on IO thread then main thread Log won't get printed.
        * */
        /* currentDate = sdf.format(Date())
         Log.d("TAG","Before run blocking $currentDate")
         runBlocking {
             Log.d("TAG"," 1 $currentDate ${Thread.currentThread().name}")
             launch {
                 currentDate = sdf.format(Date())
                 Log.d("TAG","before Launcher 1 $currentDate ${Thread.currentThread().name}")
                 delay(1000L)
                 currentDate = sdf.format(Date())
                 Log.d("TAG","Launcher 1 $currentDate")
             }
 
             launch(Dispatchers.IO) {
                 currentDate = sdf.format(Date())
                 Log.d("TAG","Before Launcher 2 $currentDate")
                 delay(2000L)
                 currentDate = sdf.format(Date())
                 Log.d("TAG","Luncher 2 $currentDate")
             }
             launch(Dispatchers.IO) {
                 currentDate = sdf.format(Date())
                 Log.d("TAG","Before Launcher 3 $currentDate")
                 delay(1500L)
                 currentDate = sdf.format(Date())
                 Log.d("TAG","Luncher 3 $currentDate")
             }
 
             currentDate = sdf.format(Date())
             Log.d("TAG","Starting Run blocking $currentDate")
 
             delay(6000L)
 
             currentDate = sdf.format(Date())
             Log.d("TAG","End Run blocking $currentDate")
         }
         currentDate = sdf.format(Date())
         Log.d("TAG","After Run blocking $currentDate")
         */


    }

    //Video 3 Suspend Function Example
    suspend fun coroutine1() {
        currentDate = sdf.format(Date())

        Log.d(
            "TAG",
            "Before coroutind 1 get delayed ${Thread.currentThread().name} ${currentDate}+ "
        )
        //To delay the execution of coroutine we can use the coroutine delay(millisecond)
        //function
        delay(7000L)

        currentDate = sdf.format(Date())
        //Here we are printing the name of the thread in which it is executing
        Log.d("TAG", "Hello From Coroutine 1${Thread.currentThread().name} ${currentDate}+ ")
    }

    suspend fun coroutine2() {
        currentDate = sdf.format(Date())

        Log.d(
            "TAG",
            "Before coroutind 2 get delayed ${Thread.currentThread().name} ${currentDate}+ "
        )
        //To delay the execution of coroutine we can use the coroutine delay(millisecond)
        //function
        delay(3000L)
        currentDate = sdf.format(Date())

        //Here we are printing the name of the thread in which it is executing
        Log.d("TAG", "Hello From Coroutine 2 ${Thread.currentThread().name} ${currentDate}+ ")
    }

    //Video 4 Coroutine Context

    suspend fun callAPI(): String {
        delay(3000L)
        return "This is the API call"
    }

    suspend fun settingText() {
        val value = callAPI()

        withContext(Dispatchers.Main) {
            txtMsg!!.text = "I'm from coroutine"
        }
    }

    //video 5 runblocking()
    //It will block the main thread if we call the delay in it
    // Checck the onCreate() commented block

    //Video 6
    //Jobs waiting and cancelling

}
