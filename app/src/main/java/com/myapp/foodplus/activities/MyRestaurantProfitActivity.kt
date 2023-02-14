package com.myapp.foodplus.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapp.foodplus.R
import com.myapp.foodplus.databinding.ActivityMyRestaurantProfitBinding
import java.util.*

class MyRestaurantProfitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyRestaurantProfitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRestaurantProfitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        binding.etCalendarBefore.setOnClickListener {
            setCalendarBefore()
        }
        binding.etCalendarAfter.setOnClickListener {
            setCalendarAfter()
        }
    }

    private fun setCalendarBefore() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { datePicker, mYear, mMonth, mDay ->
            binding.etCalendarBefore.setText("$mDay/${mMonth + 1}/$mYear")
        }, year, month, day)
        datePickerDialog.show()
    }

    private fun setCalendarAfter() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { datePicker, mYear, mMonth, mDay ->
            binding.etCalendarAfter.setText("$mDay/${mMonth + 1}/$mYear")
        }, year, month, day)
        datePickerDialog.show()
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