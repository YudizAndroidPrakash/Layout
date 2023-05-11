package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class SecondLayoutDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_layout_demo)


         findViewById<Button>(R.id.btnLinearLayout).setOnClickListener {
                  val intent = Intent(this,LinearLayoutDemoActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnRelative).setOnClickListener {
            val intent = Intent(this,RelativeLayoutDemoActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnConstraint).setOnClickListener {
            val intent = Intent(this,ConstraintLayoutDemoActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnFrameLayout).setOnClickListener {
            val intent = Intent(this,FrameLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}