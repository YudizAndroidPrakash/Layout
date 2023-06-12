package com.example.demoapp.jsondemo



import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityListOfProductsBinding
import com.example.demoapp.databinding.ActivityProductDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductDetailActivity : AppCompatActivity() {
        private lateinit var binding: ActivityProductDetailBinding
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Binding
        binding = DataBindingUtil.setContentView<ActivityProductDetailBinding?>(this,R.layout.activity_product_detail).apply {

        }



//        setContentView(R.layout.activity_product_detail)

        val pId = intent.getIntExtra("id",0)

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(GsonConverterFactory.create()).build()
            .create(ProductServiceInterface::class.java)
        val call =  retrofit.productDetails(pId)



        var viewPagerAdapter: AdapterImageViewpager
//        lateinit var viewPager: ViewPager

        call.enqueue(object : Callback<Products>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<Products>,
                response: Response<Products>
            ) {
                val productDetail = response.body()

                if(response.code() != 200 ){
                    Toast.makeText(this@ProductDetailActivity, "Some thing is wrong", Toast.LENGTH_SHORT).show()
                    return
                }
                if(productDetail!= null){
                    binding.tvProductBrandDetails.text = "Brand : ${productDetail.brand}"
                    binding.tvProductTitleDetails.text = "${productDetail.title}"
                    binding.tvProductDiscountDetails.text = "Discount : ${productDetail.discountPercentage}%"
                    binding.tvProductStockDetails.text = "Stock : ${productDetail.stock}"
                    binding.tvProductRatingDetails.text = "Rating : ${productDetail.rating}"
                    binding.tvProductDescriptionDetails.text = "Description : ${productDetail.description}"
                    binding.tvProductPriceDetails.text = "Price : ${productDetail.price}"
                    Picasso.get().load(productDetail.thumbnail).into(binding.imgThumbnailData)
                    viewPagerAdapter = AdapterImageViewpager(this@ProductDetailActivity, productDetail.images)
//                    binding.viewPager.adapter = viewPagerAdapter
                    binding.vpProductsImages.adapter = viewPagerAdapter
                }








//                findViewById<TextView>(R.id.tv_product_title_details).text = productDetail!!.title
//                findViewById<TextView>(R.id.tv_product_price_details).text =
//                    productDetail.price.toString()
//                findViewById<TextView>(R.id.tv_product_description_details).text =
//                    productDetail.description
//                findViewById<TextView>(R.id.tv_product_price_details).text =
//                    productDetail.price.toString()
//                findViewById<TextView>(R.id.tv_product_discount_details).text =
//                    productDetail.discountPercentage.toString()
//                findViewById<TextView>(R.id.tv_product_rating_details).text =
//                    productDetail.rating.toString()
//                findViewById<TextView>(R.id.tv_product_stock_details).text =
//                    productDetail.stock.toString()
//                findViewById<TextView>(R.id.tv_product_brand_details).text = productDetail.brand
//                viewPager = findViewById(R.id.vp_products_images)

//                Picasso.get().load(productDetail.thumbnail).into(profile)




            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Toast.makeText(this@ProductDetailActivity, "$t", Toast.LENGTH_SHORT).show()
            }
        })

    }
}