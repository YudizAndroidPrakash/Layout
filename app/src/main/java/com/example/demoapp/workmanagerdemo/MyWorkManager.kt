package com.example.demoapp.workmanagerdemo

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class MyWorkManager(context: Context, params: WorkerParameters) : Worker(context, params) {

    private var imageSize = 0L
    override fun doWork(): Result {
        val imageName: Long? = inputData.getLong("fileName", System.currentTimeMillis())
        val imageUrl: String? = inputData.getString("imageUrl")


//        val paramValue = "param\\with\\backslash"
//        val yourURLStr = URLEncoder.encode(imageUrl, "UTF-8")
        val url = URL(imageUrl)
        val urlSize  = url.openConnection().contentLength
        val result = getSavedFileUri(imageName!!.toString(), url.toString())
        try {
            return if (result != null) {
                for(i in 0..urlSize) {
                    Thread.sleep(imageSize / 10)
                    if (i == i * 10) {
                        setProgressAsync(workDataOf(Pair("Download", i * 10)))
                        break
                    }
                }
                 Result.success()
            } else {
                Result.failure()
            }
        } catch (e: InterruptedException) {
            Toast.makeText(applicationContext, "$e", Toast.LENGTH_SHORT).show()
        }

        return Result.retry()

//        val data1 = Data.Builder()
//            .putString("outputResult","Task Finished")
//            .build()
        // val uriOfImage = inputData.getString("iteration")//url mali jase

//      val uri =  getSavedFileUri(uriOfImage!!)

//        for(i in 1..10){
//            Log.d("$i","I value")
//            try {
//                setProgressAsync(workDataOf(Pair("progress",i*10)))
//                Thread.sleep(5000)
//                Log.d("","Running")
//            }catch (e : InterruptedException){
//                e.printStackTrace()
//                return Result.failure()
//            }
//        }
//
//        return Result.success()
//        try{
//            val wmActivity =WorkManagerActivity()
//            wmActivity.backGroundLoop()
//            return Result.success()
//        }catch (e : Exception){
//            return Result.failure()
//        }
    }


    private fun getSavedFileUri(
        fileName: String, fileUrl: String?
    ): Uri? {
        val fileName = "$fileName.jpg"
        val mimeType = "image/*"



        val data = byteArrayOf(1024.toByte())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValue = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/saveFile")
            }
//before change
//            val resolver = applicationContext.contentResolver
//            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValue)
//
//            return if (uri != null) {
//
//                URL(fileUrl).openStream().use { input ->
//                    resolver.openOutputStream(uri).use { output ->
//                        input.copyTo(output!!, DEFAULT_BUFFER_SIZE)
//
//                    }
//                }

                //after change
            val resolver = applicationContext.contentResolver
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValue)
            return if (uri != null) {
                val url = URL(fileUrl)
                val connection = url.openConnection()
                connection.content
                val sizeOfUrl = connection.contentLength
                var total = 0
                URL(fileUrl).openStream().use { input ->
                    resolver.openOutputStream(uri).use { output ->
                        var  count = input.read(data)
                        while (count != -1){
                            total += count
                            setProgressAsync(workDataOf("progress"  to ((total * 100)/sizeOfUrl)))
                            input.copyTo(output!!, DEFAULT_BUFFER_SIZE)
                            var status = output?.write(data,0,count)
                            Log.d("status","$status")
                            count =  input.read(data)
                        }
                    }
                }
                uri
            } else {
                null
            }

        } else {
            val target = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName
            )
            URL(fileUrl).openStream().use { input ->
                FileOutputStream(target).use { output ->
                    input.copyTo(output)


                }
            }
//            imageSize = target.length()
            return target.toUri()
        }

    }
//         for(i in 1..count){
//            Log.d("$i","I value")
//            try {
//                Thread.sleep(5000)
////                Log.d("","Running")
//
//            }catch (e : InterruptedException){
//                e.printStackTrace()
//                return Result.failure()
//            }
//        }
//        try{
//            val wmActivity =WorkManagerActivity()
//            wmActivity.backGroundLoop()
//            return Result.success()
//        }catch (e : Exception){
//            return Result.failure()
//        }


}