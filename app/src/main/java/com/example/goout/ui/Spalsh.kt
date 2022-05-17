package com.example.goout.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.example.goout.R

class Spalsh : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        val logo = findViewById<ImageView>(R.id.logo)
        val backround = findViewById<ImageView>(R.id.backround)
        val lottie = findViewById<LottieAnimationView>(R.id.lottie)
        backround.animate().translationY(5000F).setDuration(1000).startDelay = 4000
        logo.animate().translationY(-2000F).setDuration(1000).startDelay =4000
        lottie.animate().translationY(5000F).setDuration(1000).startDelay = 4000
        val i = Intent(applicationContext,OnBoardingActivity::class.java)
        startActivity(i)

    }
}