package com.example.demoapp.savefile

import android.content.ContentValues
import android.content.pm.PackageManager

import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.demoapp.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class SaveImageActivity : AppCompatActivity() {

    private lateinit var btnSave: Button
    private lateinit var btnSaveShare: Button
    private lateinit var ivImageDisplay: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_image)

        btnSave = findViewById(R.id.btnSaveScreenshot)
        btnSaveShare = findViewById(R.id.btnSaveScreenshotShare)
        ivImageDisplay = findViewById(R.id.ivScreenshotImage)
        val imageData = takeScreenShot(ivImageDisplay)

        btnSave.setOnClickListener {

            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
                if(takePermission()) {
                    saveImage(imageData!!)
                }else {
                    takePermission()
                }
            }else {
                saveImage(imageData!!)
            }
        }

        btnSaveShare.setOnClickListener {
//                shareImage(imageData!!)
        }


    }

    private fun saveImage(bitMapOfImage: Bitmap) {
        //name set Of Image
        val imageName = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
                }
                val imageUrl: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUrl?.let {
                    resolver.openOutputStream(it)
                }
            }
        } else {
            val imageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imageDirectory, imageName)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitMapOfImage.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
    }


    private fun takeScreenShot(ivImageDisplay: ImageView): Bitmap? {
        var image: Bitmap? = null

        try {
            image = Bitmap.createBitmap(
                ivImageDisplay.measuredWidth,
                ivImageDisplay.measuredHeight,
                Bitmap.Config.RGBA_F16
            )
            val canvas = Canvas(image)
            ivImageDisplay.draw(canvas)
        } catch (e: InterruptedException) {
            Log.e("Screenshot", "Not Captured")
        }
        return image
    }

    private  fun takePermission(): Boolean {
        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),101)
        }else {
            return true
        }
        return false
    }


//    private I fun shareImage(imageData: Bitmap) {
//            savemage(imageData)
//        val uri =
//
//
//    }


}