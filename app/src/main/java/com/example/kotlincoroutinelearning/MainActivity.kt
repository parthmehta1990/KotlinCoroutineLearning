package com.example.kotlincoroutinelearning

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        txtMsg=findViewById<TextView>(R.id.txtmsg) as TextView


        GlobalScope.launch {

            currentDate = sdf.format(Date())

            Log.d("TAG","Before coroutind get delayed ${Thread.currentThread().name} ${currentDate}+ ")
            //To delay the execution of coroutine we can use the coroutine delay(millisecond)
            //function
            delay(15000L)

            currentDate = sdf.format(Date())
            //Here we are printing the name of the thread in which it is executing
            Log.d("TAG","Hello From Coroutine ${Thread.currentThread().name} ${currentDate}+ ")

            coroutine1()
            coroutine2()

        }

        Log.d("TAG","Hello From Main Thread App ${Thread.currentThread().name}")
    }

    suspend fun coroutine1(){
        currentDate = sdf.format(Date())

        Log.d("TAG","Before coroutind 1 get delayed ${Thread.currentThread().name} ${currentDate}+ ")
        //To delay the execution of coroutine we can use the coroutine delay(millisecond)
        //function
        delay(7000L)

        currentDate = sdf.format(Date())
        //Here we are printing the name of the thread in which it is executing
        Log.d("TAG","Hello From Coroutine 1${Thread.currentThread().name} ${currentDate}+ ")
    }

    suspend fun coroutine2(){
         currentDate = sdf.format(Date())

        Log.d("TAG","Before coroutind 2 get delayed ${Thread.currentThread().name} ${currentDate}+ ")
        //To delay the execution of coroutine we can use the coroutine delay(millisecond)
        //function
        delay(3000L)
        currentDate = sdf.format(Date())

        //Here we are printing the name of the thread in which it is executing
        Log.d("TAG","Hello From Coroutine 2 ${Thread.currentThread().name} ${currentDate}+ ")
    }
}
