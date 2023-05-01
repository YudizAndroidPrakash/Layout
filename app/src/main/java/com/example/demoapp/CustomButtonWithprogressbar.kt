package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.demoapp.R
import com.example.demoapp.CustomButtonWithprogressBarClass

class CustomButtonWithprogressbar : AppCompatActivity() {
    private lateinit var btnProgressbar : CustomButtonWithprogressBarClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_button_withprogressbar)
        btnProgressbar = findViewById(R.id.customViewWithProgressbar)
        setButton()

        btnProgressbar.setOnClickListener {
//            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
            btnProgressbar.showLoader()


            Handler().postDelayed({
                btnProgressbar.setText("Success")
                btnProgressbar.hideLoader()
            },5000)

        }


    }
    private  fun setButton(){


//        btnProgressbar.setBackgroundColor(R.color.black)
//        btnProgressbar.setBackgroundColor(resources.getColor(R.color.black))
        btnProgressbar.setText("Continue")
        btnProgressbar.hideLoader()
    }
}