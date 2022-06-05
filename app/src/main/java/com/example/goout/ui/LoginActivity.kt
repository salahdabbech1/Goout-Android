package com.example.goout.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.goout.R
import com.example.goout.`interface`.KidApiInterface
import com.example.goout.`interface`.ParentApiInterface
import com.example.goout.model.Kid
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
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        //defining the variables for the view
        val email = findViewById<TextInputEditText>(R.id.EmailEditText)
        val password = findViewById<EditText>(R.id.PasswordEdit)
        val loginbtn = findViewById<Button>(R.id.LoginButton)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        val sharedprefs = getSharedPreferences("Login_prefs", MODE_PRIVATE)
        if (sharedprefs.getString("_id","")!!.isNotEmpty()) {
            Log.d("Shared prefs","this is whats in the shared prefs"+sharedprefs.getString("_id","nothing in id"))

            val i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
        }
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                loginbtn.setOnClickListener {
                    val apiInterface = KidApiInterface.create()
                    var kid = Kid()
                    kid.Email = email.text.toString()
                    kid.Password = password.text.toString()
                    apiInterface.loginkid(kid).enqueue(object : Callback<Kid> {
                        override fun onResponse(call: Call<Kid>, response: Response<Kid>) {
                            if (response.isSuccessful) {
                                Toast.makeText(applicationContext, "Kidlogin", Toast.LENGTH_SHORT)
                                    .show()
                                val i = Intent(applicationContext, TaskManagement::class.java)
                                startActivity(i)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Kidlogin unsucceful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                println("reponse body" + response.body().toString())
                                call.cancel()
                            }

                        }

                        override fun onFailure(call: Call<Kid>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Kidlogin failed",
                                Toast.LENGTH_SHORT
                            ).show()
                            call.cancel()
                        }

                    })
                }
            } else {
                loginbtn.setOnClickListener {
                    val apiInterface = ParentApiInterface.create()
                    var parent = Parent()
                    parent.Email = email.text.toString()
                    parent.Password = password.text.toString()
                    apiInterface.login(parent).enqueue(object :
                        Callback<Parent> {
                        override fun onResponse(
                            call: Call<Parent>, response:
                            Response<Parent>
                        ) {
                            if (response.code() == 201) {
                                sharedprefs.edit().apply {
                                    putString("_id", response.body()!!._id)
                                    putString("Email", response.body()!!.Email)
                                    putString("Password", response.body()!!.Password)
                                }.apply()
                                val i = Intent(applicationContext, MainActivity::class.java)
                                startActivity(i)
                            } else {
                                val builder = AlertDialog.Builder(this@LoginActivity)
                                builder.setTitle("User not found")
                                    .setMessage("the user was not found, create an account instead?")
                                    .setPositiveButton("register",
                                        DialogInterface.OnClickListener { dialog, id ->
                                            val i = Intent(
                                                applicationContext,
                                                RegisterActivity::class.java
                                            )
                                            startActivity(i)
                                        })
                                    .setNegativeButton("cancel",
                                        DialogInterface.OnClickListener { dialog, id ->
                                            dialog.cancel()
                                        })

                                // Create the AlertDialog object and return it
                                builder.create().show()
                            }
                        }

                        override fun onFailure(call: Call<Parent>, t: Throwable) {
                            val builder = AlertDialog.Builder(this@LoginActivity)
                            Log.i("erreur", t.toString())
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
    }
}





