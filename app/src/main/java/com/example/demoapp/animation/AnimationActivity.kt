package com.example.demoapp.animation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.demoapp.R
import java.io.ObjectStreamException

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        val iv : ImageView = findViewById(R.id.ivCopyright)
        findViewById<Button>(R.id.btnRotate).setOnClickListener {
            val rotate = AnimationUtils.loadAnimation(this,R.anim.rotate)
            iv.startAnimation(rotate)
            }

        findViewById<Button>(R.id.btnMove).setOnClickListener {
            val move  = AnimationUtils.loadAnimation(this,R.anim.move)
            iv.startAnimation(move)
        }
        findViewById<Button>(R.id.btnBlink).setOnClickListener {
            val blink = AnimationUtils.loadAnimation(this,R.anim.blink)
            iv.startAnimation(blink)
        }

        findViewById<Button>(R.id.btnObjectZoom).setOnClickListener {
          val animator = ObjectAnimator.ofFloat(iv,View.ROTATION_X,-360f,0f)
            animator.duration = 100
            animator.start()
        }
        findViewById<Button>(R.id.btnObjectMove).setOnClickListener {
     ObjectAnimator.ofFloat(iv,View.TRANSLATION_X,-360f,0f).apply {
         duration = 1000
         repeatMode = ObjectAnimator.REVERSE
            }.start()
        }

        findViewById<Button>(R.id.btnObjectScale).setOnClickListener {
          val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,4f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,4f)
            ObjectAnimator.ofPropertyValuesHolder(iv,scaleX,scaleY).apply {
                repeatCount = 1
                repeatMode = ObjectAnimator.REVERSE
            }.start()
        }





    }
}