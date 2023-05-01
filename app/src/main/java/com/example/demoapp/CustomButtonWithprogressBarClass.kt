package com.example.demoapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.demoapp.R
import org.w3c.dom.Text

class CustomButtonWithprogressBarClass  : ConstraintLayout {
        private  lateinit var  rootLayout : ConstraintLayout
        private lateinit var  btnLoader : TextView
        private  lateinit var  pbLoader: ProgressBar
        private  var text = ""
        private var bgColor = ""
        private  var isEnabled = true
        private  var textColor = ""
        private var showLoader = false

        constructor(context : Context) : super(context) {
            initButtonWithProgressBar(context)


        }

    constructor(context: Context,attrs : AttributeSet) : super(context,attrs){
        initButtonWithProgressBar(context)
        getStuffFromXML(context, attrs)

    }

    private  fun getStuffFromXML(context: Context,attrs: AttributeSet){

        val data = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonWithprogressBarClass)

       if(data!=null) {
           text = data.getString(R.styleable.CustomButtonWithprogressBarClass_text).toString()
           isEnabled = data.getBoolean(R.styleable.CustomButtonWithprogressBarClass_enabled,true)
           bgColor = data.getColor(R.styleable.CustomButtonWithprogressBarClass_bgColor,context.resources.getColor(R.color.white)).toString()
            textColor = data.getColor(R.styleable.CustomButtonWithprogressBarClass_textColor,context.resources.getColor(R.color.black)).toString()
            data.recycle()
       }
    }


    @SuppressLint("SuspiciousIndentation")
    private  fun initButtonWithProgressBar(context: Context) {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            LayoutInflater.from(context).inflate(R.layout.custom_button_withprogress_bar_class,this,true)
            rootLayout = findViewById(R.id.layoutforcustomdemo)
            btnLoader = findViewById(R.id.btnForCustomDemo)
            pbLoader = findViewById(R.id.pbForCustomDemo)
              if(text.isNotEmpty()){
                  btnLoader.text = text
              }
        refreshDrawableState()
        }
    fun showLoader(){
        showLoader = true
        btnLoader.visibility = View.INVISIBLE
        pbLoader.visibility = View.VISIBLE
    }
    fun hideLoader(){
     showLoader = false
      btnLoader.visibility = View.VISIBLE
     pbLoader.visibility = View.INVISIBLE
    }
    fun setText(text : String){
        btnLoader.text = text
    }
}