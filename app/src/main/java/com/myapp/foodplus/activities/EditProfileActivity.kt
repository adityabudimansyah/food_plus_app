package com.myapp.foodplus.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.myapp.foodplus.R
import com.myapp.foodplus.databinding.ActivityEditProfileBinding
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setGenderOptions()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null) {
            binding.etName.setText("${user.displayName}")
            binding.etEmail.setText("${user.email}")
        }

        binding.etBirtDate.setOnClickListener {
            setBirthDate()
        }
    }

    private fun setGenderOptions() {
        val listGender = arrayListOf("Male", "Female")
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listGender)
        binding.acGender.setAdapter(genderAdapter)
    }

    private fun setBirthDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { datePicker, mYear, mMonth, mDay ->
            binding.etBirtDate.setText("$mDay/${mMonth + 1}/$mYear")
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