package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var  btnRegistartion : Button
    lateinit var  btnProfile : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistartion = findViewById(R.id.btnRegistration)

        btnRegistartion.setOnClickListener {
            var i = Intent(this,Registration::class.java)
            startActivity(i)
        }
    }
}