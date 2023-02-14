package com.myapp.foodplus.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodplus.R
import com.myapp.foodplus.adaters.MyRestaurantMenuProductAdapter
import com.myapp.foodplus.databinding.ActivityMyRestaurantProductListBinding
import com.myapp.foodplus.models.MenuProductData

class MyRestaurantProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyRestaurantProductListBinding
    val lists = ArrayList<MenuProductData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRestaurantProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_restaurant_product_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = true
        val restaurantProductListAdapter = MyRestaurantMenuProductAdapter(lists)
        recyclerView.adapter = restaurantProductListAdapter


        lists.addAll(productList)

    }

    private val productList: ArrayList<MenuProductData>
        get() {
            val dataName = resources.getStringArray(R.array.data_menu_name)
            val dataStock = resources.getStringArray(R.array.data_menu_stock)
            val dataPrice = resources.getStringArray(R.array.data_menu_price)
            val dataImage = resources.obtainTypedArray(R.array.data_menu_photo)

            val lists = ArrayList<MenuProductData>()
            for (i in dataName.indices){
                val productData = MenuProductData(
                    dataName[i], dataStock[i].toInt(), dataPrice[i].toInt(), dataImage.getResourceId(i,-1)
                )
                lists.add(productData)
            }
            return lists
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