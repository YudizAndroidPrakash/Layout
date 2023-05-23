package com.example.demoapp.multithreading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.demoapp.R


//Create two threads to perform any operations on a number. Print the final result on the screen. (Use any mathematical operations)
//Get a number from main thread
//Perform operation in 1st thread
//Perform another operation on the result using 2nd thread
//Set the final result in a text view

class SecondThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_thread)
        val result : TextView = findViewById(R.id.tvResultOfOp)
        val btnGetResult : Button = findViewById(R.id.btnGetTheResult)
val handle = Handler()

        btnGetResult.setOnClickListener {



            Thread(Runnable {
                val one = 1
                val two = 2
                Log.d("resultone","$one")
                Log.d("resulttwo","$two")

                handle.post {
                    Thread(Runnable {
                        val resultData = one + two
                        Log.d("resultdata" ,"$resultData")
                        result.text = resultData.toString()
                        Log.d("result","$resultData")
                        handle.post{
                            result.text = resultData.toString()
                            Log.d("resutl Data","$resultData")
                            Log.d("resutl","${result.text}")
                        }
                    }).start()
                }

            }).start()
        }
    }
}