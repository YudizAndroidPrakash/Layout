package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class FrameLayoutActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_layout)

       val btnWhite =     findViewById<Button>(R.id.btnBlack)
       btnWhite.setOnClickListener {
           btnWhite.visibility = View.GONE
       }





    }
}