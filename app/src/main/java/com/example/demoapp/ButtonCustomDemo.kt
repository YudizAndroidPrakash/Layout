package com.example.demoapp

import android.content.Context
import android.text.Layout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout

class ButtonCustomDemo : LinearLayout {
    lateinit var customLayoutDemo : ConstraintLayout
    lateinit var btnButtoncustom : Button
    lateinit var pbForCustomBtn  : ProgressBar


    constructor(context: Context?) : super(context) {
        initialiazation(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
            initialiazation(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {

    }
        private  fun  initialiazation(context : Context?) {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT)
            LayoutInflater.from(context).inflate(R.layout.activity_custom_button_demo,this,true)
            customLayoutDemo = findViewById(R.id.CustomLayout)
            btnButtoncustom = findViewById(R.id.btnCustomwithProgressbar)
            pbForCustomBtn = findViewById(R.id.pbForCustom)

        }
}