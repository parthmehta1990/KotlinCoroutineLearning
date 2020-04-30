package com.example.kotlincoroutinelearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //Learning resources
    // https://www.youtube.com/watch?v=ShNhJ3wMpvQ&list=PLQkwcJG4YTCQcFEPuYGuv54nYai_lwil_&index=1

    var txtMsg: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMsg=findViewById<TextView>(R.id.txtmsg) as TextView


        GlobalScope.launch {

            //To delay the execution of coroutine we can use the coroutine delay(millisecond)
            //function
            delay(3000L)

            //Here we are printing the name of the thread in which it is executing
            Log.d("TAG","Hello From Coroutine ${Thread.currentThread().name}")

        }

        Log.d("TAG","Hello From Main Thread App ${Thread.currentThread().name}")
    }
}
