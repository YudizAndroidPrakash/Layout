package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demoapp.CustomButtonWithprogressbar
import com.example.demoapp.DrawableDemo

class MainActivity : AppCompatActivity() {
    lateinit var  btnRegistartion : Button
    lateinit var  btnProfile : Button
    lateinit var btnMView :  Button
    lateinit var  btnCustomView : Button
    lateinit var btnLayout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistartion = findViewById(R.id.btnRegistration)
        btnProfile = findViewById(R.id.btnProfile)
        btnMView = findViewById(R.id.btnCustom)
        btnCustomView = findViewById(R.id.bntcustomDemo)
        btnLayout = findViewById(R.id.btnLayout)


        btnRegistartion.setOnClickListener {
            var i = Intent(this,Registration::class.java)
            startActivity(i)
        }
        btnProfile.setOnClickListener {
            var intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }
        btnLayout.setOnClickListener {
            var intent = Intent(this,ViewDemo::class.java)
            startActivity(intent)
        }

        btnMView.setOnClickListener {
            var intent = Intent(this,DrawableDemo::class.java)
            startActivity(intent)
        }

        btnCustomView.setOnClickListener {
            var intent = Intent(this,CustomButtonWithprogressbar::class.java)
            startActivity(intent)

        }
    }
}