package com.example.demoapp.jsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.demoapp.R
import com.squareup.picasso.Picasso


class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val profile : ImageView = findViewById(R.id.imv_thumbnail_data)
        val viewPagerAdapter : AdapterImageViewpager
        lateinit var viewPager : ViewPager
        if (intent.extras != null) {
            val productData = intent.getSerializableExtra("details") as Products
            findViewById<TextView>(R.id.tv_product_title_details).text = productData.title
            findViewById<TextView>(R.id.tv_product_price_details).text =
                productData.price.toString()
            findViewById<TextView>(R.id.tv_product_description_details).text =
                productData.description
            findViewById<TextView>(R.id.tv_product_price_details).text =
                productData.price.toString()
            findViewById<TextView>(R.id.tv_product_discount_details).text =
                productData.discountPercentage.toString()
            findViewById<TextView>(R.id.tv_product_rating_details).text =
                productData.rating.toString()
            findViewById<TextView>(R.id.tv_product_stock_details).text =
                productData.stock.toString()
            findViewById<TextView>(R.id.tv_product_brand_details).text = productData.brand
            viewPager = findViewById(R.id.vp_products_images)

//            Picasso.get().load(productData.thumbnail).into(profile)
            Picasso.get().load(productData.thumbnail).into(profile)

            viewPagerAdapter = AdapterImageViewpager(this,productData.images)
            viewPager.adapter = viewPagerAdapter

        }


    }
}