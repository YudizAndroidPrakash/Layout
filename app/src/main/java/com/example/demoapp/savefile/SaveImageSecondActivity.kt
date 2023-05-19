package com.example.demoapp.savefile

import android.content.ContentValues
import android.content.Intent
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
import android.widget.Toast
import com.example.demoapp.R
import java.io.OutputStream


class SaveImageSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_image_second)

        val btnTakeScreenshot: Button = findViewById(R.id.btnSaveScreenshotSecond)
        val ivSecondScreenshot: ImageView = findViewById(R.id.ivScreenshotImageSecond)
        val btnTakeScreenshotShare: Button = findViewById(R.id.btnSaveScreenshotSecondShare)

        //take screenshot and save
        btnTakeScreenshot.setOnClickListener {
            val saveScreenShot = takeScreenshotTwo(ivSecondScreenshot)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                if (takePermission()) {
                    saveScreenshotFile(saveScreenShot)
                } else {
                    takePermission()
                }
            } else {
                saveScreenshotFile(saveScreenShot)
                Toast.makeText(this, saveScreenShot.toString(), Toast.LENGTH_LONG).show()
            }
        }

        //take screen shot ,save and share it
        btnTakeScreenshotShare.setOnClickListener {
            val saveScreenshot = takeScreenshotTwo(ivSecondScreenshot)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                val save = saveScreenshotFile(saveScreenshot)
                if (takePermission()) {
                    shareImage(save)
                } else {
                    takePermission()
                }
            } else {
                val saveHigher = saveScreenshotFile(saveScreenshot)
                shareImage(saveHigher!!)
            }
        }
    }

    //take screenshot
    private fun takeScreenshotTwo(imageView: ImageView): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(
                imageView.measuredWidth, imageView.measuredHeight, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(screenshot)
            imageView.draw(canvas)
        } catch (e: InterruptedException) {
            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
        }
        return screenshot
    }

    //save screenshot
    private fun saveScreenshotFile(bitmap: Bitmap?): Uri? {
        //file name
        val filename = "${System.currentTimeMillis()}.jpg"
        //output stream
        var fos: OutputStream?
        var imageUri: Uri? = null
        var contentValue = ContentValues()
        val resolver = contentResolver
        //API >Q            var  imageUri : Uri? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { result ->
                contentValue = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                imageUri = result.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue)
                fos = imageUri?.let {
                    result.openOutputStream(it)
                }

            }
        } else {
            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue)
            fos = imageUri?.let {
                resolver.openOutputStream(it)
            }
            fos?.use {
                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, it)
//            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show()
            }
        }
        return imageUri
    }

    //take permission
  private  fun takePermission(): Boolean {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED && checkSelfPermission(
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ), 101
            )
        } else {
            return true
        }
        return false
    }

    //save and share image
    private fun shareImage(saveImageUri: Uri?) {
        Log.d("uri", "$saveImageUri")
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, saveImageUri)
        intent.type = "image/*"
        startActivity(Intent.createChooser(intent, "share Image"))
    }
}

//API < Q
//            val imageDir =
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//            Log.d("name", filename)
//            Toast.makeText(this, filename, Toast.LENGTH_SHORT).show()
//            val image = File(imageDir, filename)
//            fos = FileOutputStream(image)
//           imageUri = Uri.fromFile(image)
//            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show()
