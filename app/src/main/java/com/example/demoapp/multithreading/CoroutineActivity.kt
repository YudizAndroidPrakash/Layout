package com.example.demoapp.multithreading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.demoapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineActivity : AppCompatActivity() {

//    Use coroutine to perform any complex operation and display the result in a text view.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        val tvSumData: TextView = findViewById(R.id.tvSum)
        val tvMulData: TextView = findViewById(R.id.tvMultiple)
        val btnGetResult: Button = findViewById(R.id.btn_get_sum)


        btnGetResult.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                "Sum ${returnSum()} ".also { tvSumData.text = it }
            }
            CoroutineScope(Dispatchers.Main).launch {
                "Mul ${returnMul()}".also{ tvMulData.text  = it }
            }

        }
    }

    suspend fun returnSum(): Int {
        var count = 0
        for (i in 0..10) {
            delay(1000)
            Log.d("sum i", "$i")
            count += i
        }
        return count
    }


    suspend fun returnMul(): Int {
        var count = 1
        Log.d("c", count.toString())
        for (i in 1..10) {
            delay(1000)
            count *= i
            Log.d("i", i.toString())
        }
        Log.d("Count mul", "$count")
        return count
    }

}