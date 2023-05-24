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



        val btnGetsum : Button = findViewById(R.id.btn_get_sum_of)
        val tvPrintSum : TextView = findViewById(R.id.tv_sum)

        btnGetsum.setOnClickListener {
            val one =20
            val two = 30
            var result = 0

            val getSum = Thread {
                result = one + two
            }
            getSum.start()
            tvPrintSum.text = result.toString()
        }


    }
}