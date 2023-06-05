package com.example.demoapp.jsondemo



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityListOfProductsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductDetailActivity : AppCompatActivity() {
        private lateinit var binding: ActivityListOfProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val pId = intent.getIntExtra("id",0)

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(GsonConverterFactory.create()).build()
            .create(ProductServiceInterface::class.java)
        val call =  retrofit.productDetails(pId)


        val profile : ImageView = findViewById(R.id.imv_thumbnail_data)
        var viewPagerAdapter: AdapterImageViewpager
        lateinit var viewPager: ViewPager

        call.enqueue(object : Callback<Products>{
            override fun onResponse(
                call: Call<Products>,
                response: Response<Products>
            ) {
                val productDetail = response.body()

                findViewById<TextView>(R.id.tv_product_title_details).text = productDetail!!.title
                findViewById<TextView>(R.id.tv_product_price_details).text =
                    productDetail.price.toString()
                findViewById<TextView>(R.id.tv_product_description_details).text =
                    productDetail.description
                findViewById<TextView>(R.id.tv_product_price_details).text =
                    productDetail.price.toString()
                findViewById<TextView>(R.id.tv_product_discount_details).text =
                    productDetail.discountPercentage.toString()
                findViewById<TextView>(R.id.tv_product_rating_details).text =
                    productDetail.rating.toString()
                findViewById<TextView>(R.id.tv_product_stock_details).text =
                    productDetail.stock.toString()
                findViewById<TextView>(R.id.tv_product_brand_details).text = productDetail.brand
                viewPager = findViewById(R.id.vp_products_images)

                Picasso.get().load(productDetail.thumbnail).into(profile)
                Picasso.get().load(productDetail.thumbnail).into(profile)

            viewPagerAdapter = AdapterImageViewpager(this@ProductDetailActivity, productDetail.images)
            viewPager.adapter = viewPagerAdapter




            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(this@ProductDetailActivity, "$t", Toast.LENGTH_SHORT).show()
            }


        })





//        val profile : ImageView = findViewById(R.id.imv_thumbnail_data)
//        val viewPagerAdapter: AdapterImageViewpager
//        lateinit var viewPager: ViewPager

//        if (intent.extras != null) {
//            val productData = intent.getSerializableExtra("details") as Products
//            findViewById<TextView>(R.id.tv_product_title_details).text = productData.title
//            findViewById<TextView>(R.id.tv_product_price_details).text =
//                productData.price.toString()
//            findViewById<TextView>(R.id.tv_product_description_details).text =
//                productData.description
//            findViewById<TextView>(R.id.tv_product_price_details).text =
//                productData.price.toString()
//            findViewById<TextView>(R.id.tv_product_discount_details).text =
//                productData.discountPercentage.toString()
//            findViewById<TextView>(R.id.tv_product_rating_details).text =
//                productData.rating.toString()
//            findViewById<TextView>(R.id.tv_product_stock_details).text =
//                productData.stock.toString()
//            findViewById<TextView>(R.id.tv_product_brand_details).text = productData.brand
//            viewPager = findViewById(R.id.vp_products_images)
//
//            Picasso.get().load(productData.thumbnail).into(profile)
//            Picasso.get().load(productData.thumbnail).into(profile)

//            binding.tvProductTitleDetails.text = productData.title
//            binding.tvProductPriceDetails.text = productData.price.toString()
//            binding.tvProductDescriptionDetails.text = productData.description
//            binding.tvProductDiscountDetails.text = productData.discountPercentage.toString()
//            binding.tvProductRatingDetails.text = productData.rating.toString()
//            binding.tvProductStockDetails.text = productData.stock.toString()
//            binding.tvProductBrandDetails.text = productData.brand
//            viewPager = binding.vpProductsImages
//
//            Picasso.get().load(productData.thumbnail).into(binding.imvThumbnailData)
//
//            viewPagerAdapter = AdapterImageViewpager(this, productData.images)
//            viewPager.adapter = viewPagerAdapter

//        }


    }
}