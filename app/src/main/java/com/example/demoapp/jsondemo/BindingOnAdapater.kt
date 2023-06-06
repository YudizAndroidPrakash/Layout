package com.example.demoapp.jsondemo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.demoapp.R
import com.squareup.picasso.Picasso

@BindingAdapter("thumbnailImage")
fun loadImage(imageView : ImageView, imageUrl : String){
    Picasso.get().load(imageUrl).placeholder(R.drawable.imgavatar).into(imageView)
}
