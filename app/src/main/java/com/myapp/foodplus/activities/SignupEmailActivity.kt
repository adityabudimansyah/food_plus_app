package com.myapp.foodplus.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.myapp.foodplus.R
import com.myapp.foodplus.databinding.ActivitySignupEmailBinding

class SignupEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupEmailBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setToolbar()

        binding.btSignupEmail.setOnClickListener {
            val fullName = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            if (fullName.isEmpty()){
                binding.etFullName.error = "Full Name is required!"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (fullName.length > 24){
                binding.etFullName.error = "Short name cannot be more than 24 characters"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
//            belum nambahin validasi tidak boleh ada angka
            if (email.isEmpty()){
                binding.etEmail.error = "Email is required!"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmail.error = "Invalid email"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (pass.isEmpty()){
                binding.etPassword.error = "Password is required!"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            if (pass.length < 8){
                binding.etPassword.error = "Minimum 8 character"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            registerUser(fullName, email, pass)
        }
    }

    private fun registerUser(fullName: String, email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(fullName)
                        .build()
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
//                                val intent = Intent(this, MainActivity::class.java)
//                                startActivities(arrayOf(intent))
                                Intent(this@SignupEmailActivity, MainActivity::class.java).also {
                                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivities(arrayOf(it))
                                }
                            } else {
                                Toast.makeText(
                                    this,
                                    "${updateTask.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@SignupEmailActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivities(arrayOf(it))
            }
        }
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}