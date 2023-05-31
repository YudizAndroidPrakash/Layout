package com.example.demoapp.jsondemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.demoapp.R
import com.squareup.picasso.Picasso
import java.util.Objects

class AdapterImageViewpager(val context: Context, private val imageList: List<String>) : PagerAdapter() {
    override fun getCount() = imageList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View =
            mLayoutInflater.inflate(R.layout.image_slider_item_products, container, false)

        val imageView: ImageView = itemView.findViewById(R.id.img_products_item)
        Picasso.get().load(imageList[position]).into(imageView)

        Objects.requireNonNull(container).addView(itemView)
        return itemView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as RelativeLayout)
    }
}