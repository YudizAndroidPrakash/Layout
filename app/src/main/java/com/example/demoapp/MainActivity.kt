package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var  btnRegistartion : Button
    lateinit var  btnProfile : Button
    lateinit var btnCutom :  Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistartion = findViewById(R.id.btnRegistration)
        btnProfile = findViewById(R.id.btnProfile)
        btnCutom = findViewById(R.id.btnCustom)
        btnRegistartion.setOnClickListener {
            var i = Intent(this,Registration::class.java)
            startActivity(i)
        }
        btnProfile.setOnClickListener {
            var intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }

        btnCutom.setOnClickListener {
            var intent = Intent(this,Custom_Button_Demo::class.java)
            startActivity(intent)
        }
    }
}