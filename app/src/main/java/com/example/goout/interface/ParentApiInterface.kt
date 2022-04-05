package com.example.goout.`interface`

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.goout.model.Parent
import retrofit2.Call
import retrofit2.http.POST

interface ParentApiInterface {
    @GET("/getall")
    fun getparents() : Call<List<Parent>>
    @POST("/Loginparent")
    fun login(): Call<Parent>
    @POST("RegisterParent")
    fun register():Call<Parent>

    companion object {

        var BASE_URL = "https://whispering-badlands-86315.herokuapp.com/Parent"

        fun create() : ParentApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ParentApiInterface::class.java)

        }
    }
}