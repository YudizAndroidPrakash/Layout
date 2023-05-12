package com.example.demoapp


import android.app.PendingIntent
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class IntentFilterActionCategoryDataActivity : AppCompatActivity() {
    private lateinit var etUrl: EditText
    private lateinit var btnOpenBrowser: Button
    private lateinit var ivImageShared: ImageView
//    private lateinit var btnsendImage: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_filter_action_category_data)
        ivImageShared = findViewById(R.id.ivSharedImages)
        etUrl = findViewById(R.id.etUrl)
        btnOpenBrowser = findViewById(R.id.btnOpenBrowser)
//        btnsendImage = findViewById(R.id.btnSendImg)

//        val getIntet = Intent.getIntent()
//        val imgData : Uri? = intent.data

        //using bundle
        if (intent.hasExtra("android.intent.extra.STREAM")) {
//           val bundle  = intent.extras
//            val strUri =Uri.parse( bundle!!.get("android.intent.extra.STREAM").toString())
//            ivImageShared.setImageURI(strUri)

            val uri = Uri.parse(intent.extras!!.get("android.intent.extra.STREAM").toString())
            ivImageShared.setImageURI(uri)
        }


        //Without bundle
//        if (intent.action == Intent.ACTION_SEND) {
//            if (intent.type!!.startsWith("image/*")) {
//        val imgUri: Uri? = intent.getParcelableExtra(Intent.EXTRA_STREAM)
//                ivImageShared.setImageURI(imgUri)
//            }
//        }
        //multiple Handle
        btnOpenBrowser.setOnClickListener {
            try {
                val intenChooser = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("https://www."+etUrl.text+".com")
//            putExtra(Intent.,data)
//            type = "text/plain"
                }
                startActivity(Intent.createChooser(intenChooser, "Choose"))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "Activity Not Found", Toast.LENGTH_SHORT).show()
            }
        }


        //Try

//        btnsendImage.setOnClickListener {
//            try {
//                val intentSendImg = Intent().apply {
//                    action = Intent.ACTION_SEND
//                    putExtra(Intent.EXTRA_TEXT,etUrl.text.toString())
//                    type = "text/plain"
//                }
//                startActivity(Intent.createChooser(intentSendImg,"send"))
//            }catch (e : ActivityNotFoundException){
//                Toast.makeText(applicationContext, "Activity not Found", Toast.LENGTH_SHORT).show()
//            }
//
//        }

    }
}