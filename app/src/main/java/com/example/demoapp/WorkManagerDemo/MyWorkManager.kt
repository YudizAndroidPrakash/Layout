package com.example.demoapp.WorkManagerDemo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorkManager(context: Context, params: WorkerParameters) : Worker(context,params){


    override fun doWork(): Result {
//        val data1 = Data.Builder()
//            .putString("outputResult","Task Finished")
//            .build()
        val count = inputData.getString("iteration")

        for(i in 1..count!!.toInt()){
            Log.d("$i","I value")
            try {
                Thread.sleep(5000)
//                Log.d("","Running")

            }catch (e : InterruptedException){
                e.printStackTrace()
                return Result.failure()
            }
        }

        return Result.success()
//        try{
//            val wmActivity =WorkManagerActivity()
//            wmActivity.backGroundLoop()
//            return Result.success()
//        }catch (e : Exception){
//            return Result.failure()
//        }

    }

}