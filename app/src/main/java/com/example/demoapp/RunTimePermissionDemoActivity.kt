package com.example.demoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class RunTimePermissionDemoActivity : AppCompatActivity() {

    private lateinit var bntPickImage: Button
    private lateinit var ivPickImage: ImageView
    private lateinit var btnNext: Button
    private var result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                Toast.makeText(applicationContext, "${it.data}", Toast.LENGTH_SHORT).show()
            } else {
                ivPickImage.setImageURI(it.data!!.data)
            }
        }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_time_permission_demo)



        bntPickImage = findViewById(R.id.btnPickImage)
        ivPickImage = findViewById(R.id.ivOfPick)
        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            val intent = Intent(this, PassMessageActivity::class.java)
//            startActivityForResult(intent, 1)

            result.launch(intent)
        }



        bntPickImage.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        permission_code
                    )

                } else {
                    pickImageFromInGellary()
                }

            } else {
                if (checkSelfPermission(android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),
                        permission_code
                    )
                }
                pickImageFromInGellary()
            }


        }
    }

    companion object {
        private const val permission_code = 1001
    }

    //handle the permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permission_code -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromInGellary()
                } else {
                    AlertDialog.Builder(this).apply {
                        setTitle("Permission")
                        setMessage("Grant a permission")
                        setPositiveButton("Ok") { dialog, _ ->

                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri: Uri = Uri.fromParts("package", packageName, null)
                            intent.data = uri
                            startActivity(intent)
                            dialog.dismiss()
                        }
                        setNegativeButton("Cancel") { dialog, _ ->
                            dialog.dismiss()
//                            finish()
                        }
                    }.show()
                }
            }
        }
    }

    private fun pickImageFromInGellary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        result.launch(intent)
    }

}