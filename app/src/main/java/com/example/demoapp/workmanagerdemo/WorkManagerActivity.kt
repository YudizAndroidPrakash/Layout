package com.example.demoapp.workmanagerdemo

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Key.PROGRESS
import androidx.work.*
import com.example.demoapp.R


class WorkManagerActivity : AppCompatActivity() {

    private lateinit var btnCancel: Button
    private lateinit var btnDownloadUrl: Button
    private lateinit var tvRunningStatus: TextView
    private lateinit var edUrl: EditText
    private lateinit var pbDownloadingProcess: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        btnCancel = findViewById(R.id.btnCancelButton)
        btnDownloadUrl = findViewById(R.id.btnDownloadFile)
        tvRunningStatus = findViewById(R.id.tvStatus)
        edUrl = findViewById(R.id.etUrlDownload)
        pbDownloadingProcess = findViewById(R.id.pbDownloadProcess)
        pbDownloadingProcess.visibility = View.GONE

//        "https://cdn.pixabay.com/photo/2016/11/29/05/45/astronomy-1867616_1280.jpg"
        val myWorkManagerDemo = WorkManager.getInstance()
        val data = Data.Builder()
            .putString(
                "imageUrl",
              "  https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Pizigani_1367_Chart_10MB.jpg/8192px-Pizigani_1367_Chart_10MB.jpg"

            )
            .putLong("fileName", System.currentTimeMillis())

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
                    val pbDownloading = it.progress.getInt(PROGRESS,0)
                    pbDownloadingProcess.progress  = pbDownloading

                    Thread(Runnable {

                    })
//                    Toast.makeText(this, "${it.progress}", Toast.LENGTH_SHORT).show()
//                    val setTheProcess = it.progress.keyValueMap.values
//                    Toast.makeText(applicationContext, "${it.progress}", Toast.LENGTH_SHORT ).show()
//                    for (i in setTheProcess) {
//                        pbDownloadingProcess.progress = i.toString().toInt()
//                    }

                }

            }

    }
}

