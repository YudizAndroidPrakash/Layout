package com.example.demoapp.animation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.example.demoapp.R

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
            val move  = AnimationUtils.loadAnimation(this,R.anim.moveright)
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



        findViewById<Button>(R.id.btnNext).setOnClickListener {
            val intent = Intent(this,SecondAnimationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.move,R.anim.move)
            // Create an intent to start the new activity
//            val intent = Intent (this, MainActivity2::class.java)
//            val options = ActivityOptions.makeCustomAnimation (this, R.anim.move,R.anim.move)

// Start the new activity with the intent and the options
//            startActivity (intent, options.toBundle ())
        }

        findViewById<Button>(R.id.btnPairAnimation).setOnClickListener {
            val intent = Intent(this,SecondAnimationActivity::class.java)
            val option = androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,androidx.core.util.Pair(iv,"pair"),androidx.core.util.Pair(iv,"pair"))
            startActivity(intent,option.toBundle())
        }

        findViewById<Button>(R.id.btnSingle).setOnClickListener {
            val intent = Intent(this,SecondAnimationActivity::class.java)
            val sharedAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(this,iv,"share")

            startActivity(intent,sharedAnimation!!.toBundle())

        }





    }
}