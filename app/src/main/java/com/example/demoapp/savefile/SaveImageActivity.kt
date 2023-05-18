package com.example.demoapp.savefile

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager

import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.Image
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
import androidx.core.content.FileProvider
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


        btnSave.setOnClickListener {
            val imageData = takeScreenShot(ivImageDisplay)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                if (takePermission()) {
                    saveImage(imageData!!)
                } else {
                    takePermission()
                }
            } else {
                saveImage(imageData!!)
            }
        }
        btnSaveShare.setOnClickListener {
            saveAndShareImage(ivImageDisplay)
        }


    }

    private fun saveImage(bitMapOfImage: Bitmap): Uri? {
        //name set Of Image
        var imageUrl: Uri? = null
        val imageName = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/*")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
                }
                imageUrl =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUrl?.let {
                    resolver.openOutputStream(it)
                }
            }
        } else {
            val imageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val imageFile = File(imageDirectory, imageName)

            imageUrl = Uri.fromFile(imageFile)
//            Toast.makeText(this, "${imageUrl!!}", Toast.LENGTH_SHORT).show()
//            Log.d("path","${imageUrl!!}")
            fos = FileOutputStream(imageFile)
        }
        fos?.use {
            bitMapOfImage.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        fos!!.flush()
        fos!!.close()
        Toast.makeText(this, "$imageUrl", Toast.LENGTH_SHORT).show()
        return imageUrl
    }


    private fun takeScreenShot(ivImageDisplay: ImageView): Bitmap? {
        var image: Bitmap? = null
        try {
            image = Bitmap.createBitmap(
                ivImageDisplay.width, ivImageDisplay.height, Bitmap.Config.ALPHA_8
            )
            val canvas = Canvas(image)
            ivImageDisplay.draw(canvas)
            Toast.makeText(this, "Screenshot", Toast.LENGTH_SHORT).show()
        } catch (e: InterruptedException) {
            Log.e("Screenshot", "Not Captured")
        }
        return image
    }

    private fun takePermission(): Boolean {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 101)
        } else {
            return true
        }
        return false
    }

    private fun saveAndShareImage(ivImageDisplay: ImageView) {
        val saveImage = takeScreenShot(ivImageDisplay)?.let { saveImage(it) }
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, saveImage)
        intent.type = "image/*"
        startActivity(Intent.createChooser(intent, "share Image"))
    }
}
