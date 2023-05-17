package com.example.demoapp.WorkManagerDemo

import android.os.Bundle
import android.view.View
import android.widget.*
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
    private lateinit var  pbDownloadingProcess : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)



        btnCancel = findViewById(R.id.btnCancelButton)
        btnDownloadUrl = findViewById(R.id.btnDownloadFile)
        tvRunningStatus = findViewById(R.id.tvStatus)
        edUrl = findViewById(R.id.etUrlDownload)
        pbDownloadingProcess = findViewById(R.id.pbDownloadProcess)
        pbDownloadingProcess.visibility = View.GONE

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
                    pbDownloadingProcess.visibility = View.VISIBLE
                    val setTheProcess = it.progress.keyValueMap.values
                    Toast.makeText(applicationContext, "${it.progress.keyValueMap.values}", Toast.LENGTH_SHORT).show()
                    for(i in setTheProcess){
                       pbDownloadingProcess.progress = i.toString().toInt()
                   }

//                   val progres = it.progress.keyValueMap.values
//                    pbDownloadingProcess.setProgress()
                }

            }

    }
}

