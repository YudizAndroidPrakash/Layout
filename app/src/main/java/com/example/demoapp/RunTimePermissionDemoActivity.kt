package com.example.demoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class RunTimePermissionDemoActivity : AppCompatActivity() {

    private  lateinit var  btnNextActivity : Button
    private  lateinit var btnPickImage : Button
    private  lateinit var  ivImage : ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_time_permission_demo)

        btnNextActivity = findViewById(R.id.btnNext)

    }
}