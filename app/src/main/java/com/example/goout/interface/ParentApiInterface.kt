package com.example.goout.`interface`

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.goout.model.Parent
import retrofit2.Call

interface ParentApiInterface {
    @GET("/")
    fun getparents() : Call<List<Parent>>

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