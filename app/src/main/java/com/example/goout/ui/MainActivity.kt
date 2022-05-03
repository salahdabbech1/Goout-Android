package com.example.goout.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.goout.R
import com.example.goout.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bottomnavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomnavigation.background = null
        bottomnavigation.menu.getItem(2).isEnabled = false
        val btnadd = findViewById<FloatingActionButton>(R.id.fab)
        bottomnavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.maps -> replaceFragment(MapsFragment())
                R.id.mykids -> replaceFragment(MyKids())



            }
            true
        }
        btnadd.setOnClickListener {
            startActivity(Intent(applicationContext,AddKidActivity::class.java))
        }

    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }

}
