package com.myapp.foodplus.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.myapp.foodplus.R
import com.myapp.foodplus.databinding.ActivityRegisterRestaurantBinding

class RegisterRestaurantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        // Note: if the user have registered as business account, then this activity will not be display
        val btRegister = findViewById<AppCompatButton>(R.id.btRegister)
        btRegister.setOnClickListener {
            val intent = Intent(this, MyRestaurantActivity::class.java)
            startActivity(intent)
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