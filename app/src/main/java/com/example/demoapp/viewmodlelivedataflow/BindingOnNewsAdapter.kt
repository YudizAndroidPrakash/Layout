package com.example.demoapp.viewmodlelivedataflow

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.demoapp.R
import com.squareup.picasso.Picasso

@BindingAdapter("newsImage")
fun ImageView.newsImageThumbnail(imageUrl :String){
    Picasso.get().load(imageUrl).into(this)
}



