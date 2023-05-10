package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class RtPermissionReturnResult : AppCompatActivity() {
    private  lateinit var  btnNext : Button
    private lateinit var ivPickere : ImageView
    private  lateinit var tvData : TextView
    private  var dataPick = registerForActivityResult(Bridge()) {
            tvData.text = it.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rt_permission_return_result)
        tvData = findViewById(R.id.tvReturnData)
//        btnNext.setOnClickListener {
//                dataPick.launch("Hello From Main ")
//        }
    }
}