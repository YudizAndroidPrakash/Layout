package com.example.demoapp.WorkManagerDemo

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class MyWorkManager(context: Context, params: WorkerParameters) : Worker(context,params){


    override fun doWork(): Result {
//        val data1 = Data.Builder()
//            .putString("outputResult","Task Finished")
//            .build()
        val uriOfImage = inputData.getString("iteration")//url mali jase

//      val uri =  getSavedFileUri(uriOfImage!!)

        for(i in 1..10){
            Log.d("$i","I value")
            try {
                setProgressAsync(workDataOf(Pair("progress",i*10)))
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





//    private  fun getSavedFileUri(uriOfImage : String,context: Context = applicationContext) : Uri?{
//        val mimeType = "image/*"
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//            val  contentValues = ContentValues().apply {
//                put(MediaStore.MediaColumns.DISPLAY_NAME,"taskImage")
//                put(MediaStore.MediaColumns.MIME_TYPE,mimeType)
//                put(MediaStore.MediaColumns.RELATIVE_PATH,"Download/DownloadDemo")
//            }
//            val resolver = context.contentResolver
//            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI,contentValues)
//
//            return if(uri!=null){
//                URL(uriOfImage).openStream().use { input ->
//                    resolver.openOutputStream(uri).use {output ->
//                        input.copyTo(output!!, DEFAULT_BUFFER_SIZE)
//                    }
//                }
//                uri
//            }else {
//                null
//            }
//        }else {
//            val target = File(
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),uriOfImage
//            )
//            URL(uriOfImage).openStream().use { input ->
//                FileOutputStream(target).use{
//                    output -> input.copyTo(output)
//                }
//            }
//            return target.toUri()
//        }
//
//
//
//    }
//





}