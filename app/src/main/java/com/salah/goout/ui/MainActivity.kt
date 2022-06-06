package com.salah.goout.ui

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.salah.goout.R
import com.salah.goout.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onBackPressed() {
        var sharedprefs : SharedPreferences = getSharedPreferences("Login_prefs", MODE_PRIVATE)
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Logging out")
            .setMessage("Are you sure you want to logout")
            .setPositiveButton("yes",
                DialogInterface.OnClickListener { dialog, id ->
                    sharedprefs.edit().clear().apply()
                    println(sharedprefs.getString("_id","no id"))
                    this.finishAffinity()
                })
            .setNegativeButton("no",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

        // Create the AlertDialog object and return it
        builder.create().show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bottomnavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomnavigation.background = null
        bottomnavigation.menu.getItem(2).isEnabled = false
        val btnadd = findViewById<FloatingActionButton>(R.id.fab)
        val animation = AnimationUtils.loadAnimation(this,R.anim.circulare_explosion).apply {
            duration = 200
            interpolator = AccelerateDecelerateInterpolator()
        }
        bottomnavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mykids -> replaceFragment(MyKids())
                R.id.settings -> replaceFragment(SettingsFragment())



            }
            true
        }
        binding.fab.setOnClickListener {
            binding.fab.isVisible = false
            binding.circle.isVisible = true
            binding.circle.startAnimation(animation){
                startActivity(Intent(applicationContext, AddKidActivity::class.java))
                binding.fab.isExpanded = true
            }
        }


    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }

}
