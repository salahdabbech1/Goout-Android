package com.salah.goout.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.salah.goout.R
import com.salah.goout.lists.Onboarding.OnboardingItemsAdapter
import com.salah.goout.model.Onboarding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorContainer:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        setonboarding()
        setupindicator()
        setupcurrindicator(0)
    }
    private fun setonboarding(){
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                Onboarding(
                    Onboardingimage = R.drawable.undraw_completed_tasks_vs6q,
                    Title = "Chores",
                    Description = "Give you child tasks to do and reward them as you wish"
                ),
                Onboarding(
                    Onboardingimage = R.drawable.undraw_chatting_re_j55r,
                    Title = "Chat",
                    Description = "Talk to your kids as you wish"
                ),
                Onboarding(
                    Onboardingimage = R.drawable.undraw_location_tracking_re_n3ok,
                    Title = "Track",
                    Description = "track your kids location"
                )
            )
        )
        val onboardingviewpager = findViewById<ViewPager2>(R.id.onboardingviewpager)
        onboardingviewpager.adapter = onboardingItemsAdapter
        onboardingviewpager.registerOnPageChangeCallback(object:
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                setupcurrindicator(position)
            }
        })
        (onboardingviewpager.getChildAt(0)as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.next).setOnClickListener {
            if(onboardingviewpager.currentItem+1<onboardingItemsAdapter.itemCount){
                onboardingviewpager.currentItem += 1

            }else{
                navigate()
            }
            findViewById<TextView>(R.id.textskip).setOnClickListener {  navigate() }
            findViewById<Button>(R.id.getstarted).setOnClickListener { navigate() }
        }
    }
    private fun setupindicator(){
        indicatorContainer = findViewById(R.id.indicators)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutparams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT,
            WRAP_CONTENT)
        layoutparams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let{
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_backround
                    )
                )
                it.layoutParams = layoutparams
                indicatorContainer.addView(it)

            }
        }

    }
    private fun setupcurrindicator(position:Int){
        val childcount = indicatorContainer.childCount
        for (i in 0 until childcount ){
        val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_backround,

                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_backround
                    )
                )
            }
        }
    }
    private fun navigate(){
        startActivity(Intent(applicationContext,LoginActivity::class.java))
        finish()
    }
}