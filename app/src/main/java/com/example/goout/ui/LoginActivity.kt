package com.example.goout.ui

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.*
import com.example.goout.R
import com.example.goout.`interface`.ParentApiInterface
import com.example.goout.model.Parent
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //setting the bottom sheet behavior to start collapsed
        BottomSheetBehavior.from(findViewById(R.id.bottom_sheet)).apply {
            peekHeight=200
            this.state=BottomSheetBehavior.STATE_COLLAPSED
        }
        //defining the variables for the view
        val email = findViewById<TextInputEditText>(R.id.EmailEditText)
        val password = findViewById<EditText>(R.id.PasswordEdit)
        val loginbtn = findViewById<Button>(R.id.LoginButton)
        val sharedprefs = getSharedPreferences("Login_prefs", MODE_PRIVATE)
        if (sharedprefs != null) {
            Log.d("Shared prefs","this is whats in the shared prefs"+sharedprefs.getString("_id","nothing in id"))

            val i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
        }
        loginbtn.setOnClickListener {
            val apiInterface = ParentApiInterface.create()
            var parent = Parent()
            parent.Email= email.text.toString()
            parent.Password = password.text.toString()
            println(parent)
            apiInterface.login(parent).enqueue(object :
                Callback<Parent> {
                override fun onResponse(call: Call<Parent>, response:
            Response<Parent>) {
                    println("reponse body"+ response.body().toString())

                    if (response.code()==201){
                        sharedprefs.edit().apply {
                            putString("_id", response.body()!!._id)
                            putString("Email", response.body()!!.Email)
                            putString("Password",response.body()!!.Password)
                        }.apply()
                        val i = Intent(applicationContext, MainActivity::class.java)
                        startActivity(i)
                    }
                    else if (response.code()==400){
                        val builder = AlertDialog.Builder(this@LoginActivity)
                        builder.setTitle("login failed")
                            .setMessage("the user was not found, create an account instead?")
                            .setPositiveButton("register",
                                DialogInterface.OnClickListener { dialog, id ->
                                    val i = Intent(applicationContext, RegisterActivity::class.java)
                                    startActivity(i)
                                })
                            .setNegativeButton("cancel",
                                DialogInterface.OnClickListener { dialog, id ->
                                    dialog.cancel()
                                })

                        // Create the AlertDialog object and return it
                        builder.create().show()
                    }
                    else{
                        println("ay 7aja ")
                    }

                    }

                override fun onFailure(call: Call<Parent>, t: Throwable) {
                    val builder = AlertDialog.Builder(this@LoginActivity)
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



    }}}





