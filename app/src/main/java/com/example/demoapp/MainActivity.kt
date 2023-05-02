package com.example.demoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demoapp.CustomButtonWithprogressbar
import com.example.demoapp.DrawableDemo
import com.google.android.material.tabs.TabLayout.Tab

class MainActivity : AppCompatActivity() {
    lateinit var  btnRegistartion : Button
    lateinit var  btnProfile : Button
    lateinit var btnMView :  Button
    lateinit var  btnCustomView : Button
    lateinit var btnLayout : Button
    lateinit var btnVectorShapeDemo : Button
   lateinit var tabLayoutDemo  : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistartion = findViewById(R.id.btnRegistration)
        btnProfile = findViewById(R.id.btnProfile)
        btnMView = findViewById(R.id.btnCustom)
        btnCustomView = findViewById(R.id.bntcustomDemo)
        btnLayout = findViewById(R.id.btnLayout)
        btnVectorShapeDemo = findViewById(R.id.btnVectorShapeSelector)
        tabLayoutDemo = findViewById(R.id.tlDemoButton)
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

        btnVectorShapeDemo.setOnClickListener {
            var intent  = Intent(this,VectorShapeSelector::class.java)
            startActivity(intent)
        }

        tabLayoutDemo.setOnClickListener {
            var intent = Intent(this,TabLayoutDemo::class.java)
            startActivity(intent)
        }

    }
}