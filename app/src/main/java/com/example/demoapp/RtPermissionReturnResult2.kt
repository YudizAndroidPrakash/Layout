package com.example.demoapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class RtPermissionReturnResult2 : AppCompatActivity() {
    private lateinit var tvHeading: TextView
    private lateinit var btnImagePick: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rt_permission_return_result2)
        btnImagePick = findViewById(R.id.btnImagePick)
        val getTheImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            
        }

        btnImagePick.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1
                    )
                } else {
                    imagePicker()

                }
            } else {
                imagePicker()
            }
        }

    }

    private fun imagePicker() {
        Toast.makeText(applicationContext, "Images", Toast.LENGTH_SHORT).show()
    }

}