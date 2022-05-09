package com.example.goout.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.goout.R
import com.example.goout.`interface`.ParentApiInterface
import com.example.goout.model.Parent
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name = findViewById<EditText>(R.id.nametext)
        val email = findViewById<EditText>(R.id.emailtext)
        val password = findViewById<EditText>(R.id.passwordtext)
        var registerbtn = findViewById<Button>(R.id.registerbtn)
        val apiInterface = ParentApiInterface.create()
        var parent = Parent()
        val sharedprefs = getSharedPreferences("Login_prefs", MODE_PRIVATE)
        registerbtn.setOnClickListener {
            parent.Name = name.text.toString()
            parent.Email = email.text.toString()
            parent.Password = password.text.toString()
            apiInterface.register(parent).enqueue(object : Callback<Parent>{
                override fun onResponse(call: Call<Parent>, response: Response<Parent>) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,"Your account was created welcome",Toast.LENGTH_SHORT).show()
                        sharedprefs.edit().apply {
                            putString("_id", response.body()!!._id)}.apply()
                        val i = Intent(applicationContext, MainActivity::class.java)
                        startActivity(i)
                    }
                    else{
                        Toast.makeText(applicationContext,"An error occured, please retry",Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<Parent>, t: Throwable) {
                    val builder = AlertDialog.Builder(this@RegisterActivity)
                    Log.i("erreur",t.toString())
                    builder.setTitle("login failed")
                        .setMessage("An error has occured, try connecting to the internet")
                        .setNegativeButton("ok",
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })

                    // Create the AlertDialog object and return it
                    builder.create().show()
                }

            })
        }
    }
}