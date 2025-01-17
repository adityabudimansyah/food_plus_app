package com.myapp.foodplus.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.myapp.foodplus.R
import com.myapp.foodplus.activities.EditProfileActivity
import com.myapp.foodplus.activities.RegisterRestaurantActivity
import com.myapp.foodplus.activities.SigninActivity
import com.myapp.foodplus.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null) {
            binding.tvUserName.text = "${user.displayName}"
            binding.tvUserEmail.text = "${user.email}"
        }

        // Note: if the user have registered as business account, then the button's text into "My Restaurant"
        binding.btConnectRestaurant.setOnClickListener {
            val intent = Intent(context, RegisterRestaurantActivity::class.java)
            startActivity(intent)
        }
        binding.btLogOut.setOnClickListener {
            showDeleteConfirmation()
        }
        binding.btEditProfile.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.btSecurityAndAccount.setOnClickListener {
            Toast.makeText(requireContext(), "Under Development", Toast.LENGTH_SHORT).show()
        }
        binding.btMyAddress.setOnClickListener {
            Toast.makeText(requireContext(), "Under Development", Toast.LENGTH_SHORT).show()
        }
        binding.btPaymentMethod.setOnClickListener {
            Toast.makeText(requireContext(), "lagi Development", Toast.LENGTH_SHORT).show()
        }
        binding.btLanguage.setOnClickListener {
            Toast.makeText(requireContext(), "Under Development", Toast.LENGTH_SHORT).show()
        }
        binding.btAboutUs.setOnClickListener {
            Toast.makeText(requireContext(), "Under Development", Toast.LENGTH_SHORT).show()
        }
        binding.btGiveRatings.setOnClickListener {
            Toast.makeText(requireContext(), "Under Development", Toast.LENGTH_SHORT).show()
        }
        binding.btHelp.setOnClickListener {
            Toast.makeText(requireContext(), "Under Development", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDeleteConfirmation() {
        val dialog = Dialog(requireContext())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_logout_confirmation)

        val tvCancel = dialog.findViewById<TextView>(R.id.tvCancel)
        val tvExit = dialog.findViewById<TextView>(R.id.tvExit)

        tvCancel.setOnClickListener { dialog.dismiss()
        }
        tvExit.setOnClickListener {
            auth.signOut()
            Intent(context, SigninActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
        dialog.show()
    }


}