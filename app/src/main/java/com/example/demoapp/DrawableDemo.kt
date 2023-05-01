package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.demoapp.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
class DrawableDemo : AppCompatActivity() {
    lateinit var btnClick : Button
    lateinit var tvClick : TextView
    lateinit var layoutDemo : ConstraintLayout
    lateinit var fbFirst : FloatingActionButton
    lateinit var fbSecond : FloatingActionButton
    lateinit var  fbThird : FloatingActionButton
    private var fbVisible = false
    lateinit var fbaBtnMain : ExtendedFloatingActionButton
    lateinit var fbBtnTrain  : ExtendedFloatingActionButton
    lateinit var  fbBtnMotor : ExtendedFloatingActionButton
    private  var fbaVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_demo)

        btnClick = findViewById(R.id.snkDemo)
        tvClick = findViewById(R.id.tvSnackEvent)
        tvClick.visibility = View.GONE
        layoutDemo = findViewById(R.id.demoLayout)
        fbFirst  = findViewById(R.id.fbfirst)
        fbSecond = findViewById(R.id.fbsecond)
        fbThird = findViewById(R.id.fbthird)
        fbaBtnMain = findViewById(R.id.fbaWalk)
        fbBtnTrain = findViewById(R.id.fbaTrain)
        fbBtnMotor = findViewById(R.id.fbaMotor)

        fbVisible = false
        fbaVisible = false




        btnClick.setOnClickListener {
            if(tvClick.visibility == View.GONE){
                textHideandShow("Display Text",View.VISIBLE)
            }else {
                textHideandShow("Hide Text",View.GONE)
            }
        }

        fbFirst.setOnClickListener {
            if(!fbVisible){
                fbThird.show()
                fbSecond.show()

                fbThird.visibility = View.VISIBLE
                fbSecond.visibility = View.VISIBLE
                fbFirst.setImageDrawable(resources.getDrawable(R.drawable.baseline_emoji_emotions_24))

                fbVisible = true
            }else {
                fbThird.hide()
                fbSecond.hide()
                fbThird.visibility = View.GONE
                fbSecond.visibility = View.GONE

                fbVisible = false
                fbFirst.setImageDrawable(resources.getDrawable(R.drawable.baseline_add_a_photo_24))
            }

        }



        fbaBtnMain.setOnClickListener {
            if(!fbaVisible){
                fbBtnTrain.show()
                fbBtnMotor.show()
                fbBtnTrain.visibility = View.VISIBLE
                fbBtnMotor.visibility = View.VISIBLE
                fbaVisible = true
            }else {
                fbBtnTrain.hide()
                fbBtnMotor.hide()
                fbBtnTrain.visibility = View.VISIBLE
                fbBtnMotor.visibility = View.VISIBLE
                fbaVisible = false


            }
        }
    }
    private fun textHideandShow(displayText : String,status : Int){
        val snack = Snackbar.make(layoutDemo,"Button Clicked",Snackbar.LENGTH_LONG)
            .setAction(displayText){
                tvClick.visibility = status
            }
        snack.show()
    }

}