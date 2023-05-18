package com.example.demoapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demoapp.workmanagerdemo.WorkManagerActivity
import com.example.demoapp.savefile.SaveImageActivity
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
   private lateinit var btnRegistartion: Button
    private  lateinit var btnProfile: Button
    private lateinit var btnMView: Button
    private lateinit var btnCustomView: Button
    private lateinit var btnLayout: Button
    private lateinit var btnVectorShapeDemo: Button
    private lateinit var tabLayoutDemo: Button
    private lateinit var rvDemo: Button
    private lateinit var btnRuntime : Button
    private lateinit var btnFragment : Button
    private lateinit var btnAlertDialog : Button
    private  lateinit var btnNotification :Button
    private  lateinit var btnIntentFilter :Button
    private  lateinit var btnBroadCastReceiver: Button
    private  lateinit var btnWorkManagerDemo : Button
    private  lateinit var btnSaveImageToDevice : Button

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
        btnIntentFilter = findViewById(R.id.btnIntentFilter)
        btnBroadCastReceiver = findViewById(R.id.btnForBroadCastReceiver)
        btnWorkManagerDemo = findViewById(R.id.btnWorkManager)
        btnSaveImageToDevice = findViewById(R.id.btnSaveImageToDevice)


        btnBroadCastReceiver.setOnClickListener {
            val  i = Intent(this,BroadCastReceiverOne::class.java)
            startActivity(i)
        }


        btnRegistartion.setOnClickListener {
            val i = Intent(this, Registration::class.java)
            startActivity(i)
        }
        btnProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        btnLayout.setOnClickListener {
            val intent = Intent(this,SecondLayoutDemo::class.java)
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
            val intent = Intent(this,RunTimePermissionDemoActivity::class.java)
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

        btnIntentFilter.setOnClickListener {
            val intent = Intent(this,IntentFilterActionCategoryDataActivity :: class.java)
            startActivity(intent)
        }



        btnWorkManagerDemo.setOnClickListener {
            val i = Intent(this,WorkManagerActivity::class.java)
            startActivity(i)
        }




        btnSaveImageToDevice.setOnClickListener {
            val intent = Intent(this,SaveImageActivity::class.java)
            startActivity(intent)
        }



    }
}