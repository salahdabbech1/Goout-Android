package com.salah.goout.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.salah.goout.R


import com.salah.goout.`interface`.ParentApiInterface
import com.salah.goout.model.Kid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddKidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kid)
        val name = findViewById<EditText>(R.id.nametext)
        val email = findViewById<EditText>(R.id.emailtext)
        val password = findViewById<EditText>(R.id.passwordtext)
        var registerbtn = findViewById<Button>(R.id.registerbtn)
        val apiInterface = ParentApiInterface.create()
        var kid = Kid()
        val sharedprefs = getSharedPreferences("Login_prefs", MODE_PRIVATE)
        registerbtn.setOnClickListener {
            kid.Name = name.text.toString()
            kid.Email = email.text.toString()
            kid.Password = password.text.toString()

            apiInterface.registerkid(sharedprefs.getString("_id","no id").toString(),kid).enqueue(object :
                Callback<Kid>{
                override fun onResponse(call: Call<Kid>, response: Response<Kid>) {
                    Toast.makeText(applicationContext,"Your child's account was created, congratulations!", Toast.LENGTH_SHORT)
                    val i = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                }

                override fun onFailure(call: Call<Kid>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong, please try again", Toast.LENGTH_SHORT)
                }

            })
        }
    }
}