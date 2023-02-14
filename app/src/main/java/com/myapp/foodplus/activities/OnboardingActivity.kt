package com.myapp.foodplus.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.myapp.foodplus.R
import com.myapp.foodplus.adaters.PagerAdapter
import com.myapp.foodplus.databinding.ActivityOnboardingBinding
import com.myapp.foodplus.models.OnBoardingData

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private var itemList = ArrayList<OnBoardingData>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setUpViewPager()
    }

    private fun setUpViewPager() {
        itemList = getItems()
        binding.viewpager.adapter = PagerAdapter(itemList)
        binding.wormDot.attachTo(binding.viewpager)
        binding.viewpager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private var pageChangeCallback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if(position == itemList.size -1) {
                binding.tvNext.text = "Let's save the food"
            } else {
                binding.tvNext.text = "Next!"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@OnboardingActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivities(arrayOf(it))
            }
        }
    }

    private fun getItems(): ArrayList<OnBoardingData> {
        val items = ArrayList<OnBoardingData>()

        items.add(
            OnBoardingData(
                "Let’s together save food",
                "Every food that is wasted can damage\n" +
                        "the environment and our planet so\n" +
                        "Let's together save the food ",
                R.drawable.il_motherearthdaypana
            )
        )

        items.add(
            OnBoardingData(
                "Get tasty food affordabbly!",
                "This app will help yo find\n" +
                        "delicious food at affordable prices while\n" +
                        "protecting the environment",
                R.drawable.il_shawarmapana
            )
        )

        items.add(
            OnBoardingData(
                "Find the best meal near you",
                "You can find the best meal  with best deals\n" +
                        "near you, pay in the app and\n" +
                        "pick up your food",
                R.drawable.il_orderfoodpana
            )
        )
        return items
    }

    fun onClick(v: View) {
        when (v) {
            binding.tvNext -> {
                val currentItemPosition = binding.viewpager.currentItem
                if (currentItemPosition == itemList.size - 1) {
                    val intent = Intent(this@OnboardingActivity, SigninActivity::class.java)
                    startActivity(intent)
                    return
                }
                binding.viewpager.setCurrentItem(currentItemPosition + 1, true)
            }
        }
    }
}