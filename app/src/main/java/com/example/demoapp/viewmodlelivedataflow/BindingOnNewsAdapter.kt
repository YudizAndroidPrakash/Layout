package com.example.demoapp.viewmodlelivedataflow

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.demoapp.R
import com.squareup.picasso.Picasso

@BindingAdapter("newsImage")
fun newsImageThumbnail(imageView: ImageView,imageUrl :String){
    Picasso.get().load(imageUrl).into(imageView)
}