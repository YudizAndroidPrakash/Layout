package com.example.demoapp.multithreading

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.demoapp.R
import kotlin.math.log
import kotlin.properties.Delegates

//Create a thread that adds 2 numbers and then set the result in a text view.
class SingleThreadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_thread)


        try {
            Runnable {
                val msg : TextView  = findViewById(R.id.tvOutputSum)
                Toast.makeText(this, "runcall", Toast.LENGTH_SHORT).show()
                msg.text = "HEllo"
            }.run()
        }catch (e : java.lang.Exception){
            e.message
        }

//                val btnPerformOp : Button =findViewById(R.id.btnSumOperation)
//
//      btnPerformOp.setOnClickListener {
//
//        val firstValue = 10
//            val secondValue = 20
//            var sum = 0
//            try {
//                val threadDemo  = Runnable {
//                     sum = firstValue + secondValue
//                }
//                threadDemo.run()
////                tvOutputData.text = sum.toString()
//            }catch (e : InterruptedException){
//                e.message
//            }
//        }
    }
}