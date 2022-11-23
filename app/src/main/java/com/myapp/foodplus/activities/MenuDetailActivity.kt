package com.myapp.foodplus.activities

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapp.foodplus.R
import com.myapp.foodplus.databinding.ActivityMenuDetailBinding
import com.myapp.foodplus.models.MenuData

class MenuDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        // Get Data with intent and set to view
        val menuData = intent.getParcelableExtra<MenuData>("OBJECT_INTENT")
        binding.tvMenuTitle.text = menuData?.name
        binding.tvStockAmount.text = "${menuData?.stock} left"
        binding.tvPrice.text = "Rp${menuData?.price}"
        binding.tvDescription.text = menuData?.description
        binding.tvPriceNormal.text = "Rp${menuData?.normalPrice}"
        binding.tvPriceNormal.paintFlags = binding.tvPriceNormal.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG // set strike text
        binding.tvExpire.text = menuData?.expireDate

        binding.btAdd.setOnClickListener {
            finish()
        }
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