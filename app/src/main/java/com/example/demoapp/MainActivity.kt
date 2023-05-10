package com.example.demoapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
    lateinit var btnRegistartion: Button
    lateinit var btnProfile: Button
    lateinit var btnMView: Button
    lateinit var btnCustomView: Button
    lateinit var btnLayout: Button
    lateinit var btnVectorShapeDemo: Button
    lateinit var tabLayoutDemo: Button
    lateinit var rvDemo: Button
    lateinit var btnRuntime : Button
    lateinit var btnFragment : Button
    lateinit var btnAlertDialog : Button
    lateinit var btnNotification :Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DynamicColors.applyToActivitiesIfAvailable(applicationContext as Application)
        setContentView(R.layout.activity_main)

        btnRegistartion = findViewById(R.id.btnRegistration)
        btnProfile = findViewById(R.id.btnProfile)
        btnMView = findViewById(R.id.btnCustom)
        btnCustomView = findViewById(R.id.bntcustomDemo)
        btnLayout = findViewById(R.id.btnLayout)
        btnVectorShapeDemo = findViewById(R.id.btnVectorShapeSelector)
        tabLayoutDemo = findViewById(R.id.tlDemoButton)
        rvDemo = findViewById(R.id.rvDemo)
        btnRuntime = findViewById(R.id.btnRunTime)
        btnFragment = findViewById(R.id.btnFragment)
        btnAlertDialog = findViewById(R.id.btnAlertDialog)
        btnNotification = findViewById(R.id.btnNotification)


        btnRegistartion.setOnClickListener {
            val i = Intent(this, Registration::class.java)
            startActivity(i)
        }
        btnProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        btnLayout.setOnClickListener {
            val intent = Intent(this,ViewDemo::class.java)
            startActivity(intent)
        }

        btnMView.setOnClickListener {
            val intent = Intent(this, DrawableDemo::class.java)
            startActivity(intent)
        }

        btnCustomView.setOnClickListener {
            val intent = Intent(this, CustomButtonWithprogressbar::class.java)
            startActivity(intent)

        }

        btnVectorShapeDemo.setOnClickListener {
            val intent = Intent(this, VectorShapeSelector::class.java)
            startActivity(intent)
        }

        tabLayoutDemo.setOnClickListener {
            val intent = Intent(this, TabLayoutDemo::class.java)
            startActivity(intent)
        }


        rvDemo.setOnClickListener {
            val intent = Intent(this, RecycleViewDemo::class.java)
            startActivity(intent)
        }


        btnRuntime.setOnClickListener {
            val intent = Intent(this,RtPermissionReturnResult::class.java)
            startActivity(intent)
        }

        btnFragment.setOnClickListener {
            val intent = Intent(this,FragmentTaskActivity::class.java)
            startActivity(intent)
        }

        btnAlertDialog.setOnClickListener {
            val intent = Intent(this,AlertDialogActivity::class.java)
            startActivity(intent)
        }

        btnNotification.setOnClickListener {
            val intent = Intent(this,NotificationActivity::class.java)
            startActivity(intent)
        }

    }
}