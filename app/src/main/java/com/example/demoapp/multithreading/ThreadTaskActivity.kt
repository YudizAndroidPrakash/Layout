package com.example.demoapp.multithreading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demoapp.R

class ThreadTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_task)

        findViewById<Button>(R.id.btnFirstThread).setOnClickListener {
            startActivity(Intent(this, SingleThreadActivity::class.java))
        }

        findViewById<Button>(R.id.btnSecondThread).setOnClickListener {
            startActivity(Intent(this, SecondThreadActivity::class.java))
        }

        findViewById<Button>(R.id.btnAsync).setOnClickListener {
            startActivity(Intent(this,AsyncActivity::class.java))
        }

        findViewById<Button>(R.id.btnCoroutine).setOnClickListener {
            startActivity(Intent(this,CoroutineActivity::class.java))
        }
    }
}