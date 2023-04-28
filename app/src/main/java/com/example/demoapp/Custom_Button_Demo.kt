package com.example.demoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout

class Custom_Button_Demo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_button_demo)


        val cbp = findViewById<ButtonCustomDemo>(R.id.CustomLayout)
        cbp.btnButtoncustom.setText("Click")

    }
}