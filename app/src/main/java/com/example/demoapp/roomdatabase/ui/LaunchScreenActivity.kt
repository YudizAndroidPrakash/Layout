package com.example.demoapp.roomdatabase.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.example.demoapp.R
import org.w3c.dom.Text

class LaunchScreenActivity : AppCompatActivity() {
    private lateinit var pbLoadingScreen: ProgressBar
    private lateinit var tvShowProgress: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        pbLoadingScreen = findViewById(R.id.pb_loading)
        tvShowProgress = findViewById(R.id.tv_show_progress)


        val getSharedPreferencesData = getSharedPreferences("user_details", Context.MODE_PRIVATE)
        val values = getSharedPreferencesData.all.values.isEmpty()
        var i = 0
        tvShowProgress.text = i.toString()
        val handler = Handler()

        Thread(Runnable {
            while (i != 100) {
                i += 10
                handler.post (Runnable{
                    pbLoadingScreen.progress = i
                    tvShowProgress.text = i.toString()
                })

                try {
                    Thread.sleep(2000)
                    if(i==100) {
                        if (values) {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }
                    }
                }catch (e  : InterruptedException){
                    e.message
                }
            }
        }).start()





//        Handler().postDelayed({
//
//        }, 10000)
    }
}