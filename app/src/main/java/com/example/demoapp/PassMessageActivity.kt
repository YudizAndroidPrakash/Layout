package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class PassMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_message)


        val tvTile : EditText = findViewById(R.id.etTitle)
        val btnSend : Button = findViewById(R.id.btnSendData)
        btnSend.setOnClickListener {
            val  intent = Intent()
            intent.putExtra("title",tvTile.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }

    }
}