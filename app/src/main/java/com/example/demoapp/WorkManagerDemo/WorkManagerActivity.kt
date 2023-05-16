package com.example.demoapp.WorkManagerDemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.demoapp.R


class WorkManagerActivity : AppCompatActivity() {

    private lateinit var btnCancel: Button
    private  lateinit var  btnDownloadUrl : Button
    private  lateinit var tvRunningStatus : TextView
    private lateinit var edUrl : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)



        btnCancel = findViewById(R.id.btnCancelButton)
        btnDownloadUrl = findViewById(R.id.btnDownloadFile)
        tvRunningStatus = findViewById(R.id.tvStatus)
        edUrl = findViewById(R.id.etUrlDownload)
        var myWorkManagerDemo = WorkManager.getInstance()
        val data = Data.Builder()
            .putString("iteration",edUrl.text.toString())
            .build()

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val myWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(MyWorkManager::class.java)
            .setInputData(data)
            .setConstraints(constraint)
            .build()

        btnDownloadUrl.setOnClickListener {
            myWorkManagerDemo.enqueue(myWorkRequest)
        }
        btnCancel.setOnClickListener {
          myWorkManagerDemo.cancelWorkById(myWorkRequest.id)
        }

        myWorkManagerDemo.getWorkInfoByIdLiveData(myWorkRequest.id)
            .observe(this) {
                if (it != null) {
                    tvRunningStatus.text = it.state.toString()
                }
            }
    }
}

