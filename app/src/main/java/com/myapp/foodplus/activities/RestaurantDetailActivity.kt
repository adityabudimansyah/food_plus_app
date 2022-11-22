package com.myapp.foodplus.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapp.foodplus.R
import com.myapp.foodplus.databinding.ActivityRestaurantDetailBinding
import com.myapp.foodplus.models.RestaurantData

class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        // Get Data with intent
        val restaurantData = intent.getParcelableExtra<RestaurantData>("OBJECT_INTENT")

        binding.tvRestaurantName.text = restaurantData!!.name
        binding.tvRestaurantHighlight.text = restaurantData.highlight
        binding.ivRestaurantImage.setImageResource(restaurantData.image)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}