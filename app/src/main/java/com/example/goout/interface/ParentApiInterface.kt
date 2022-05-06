package com.example.goout.`interface`

import com.example.goout.model.Kid

import com.example.goout.model.Parent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ParentApiInterface {
    @GET("/getall")
    fun getparents() : Call<List<Parent>>

    @GET("/parent/{id}/getKids")
    fun getkids(@Path(value = "id") id: String): Call<List<Kid>>

    @POST("/parent/loginparent")
    fun login(@Body parent : Parent): Call<Parent>

    @POST("/RegisterParent")
    fun register(@Body() parent : Parent):Call<Parent>



    companion object {

        //var BASE_URL = "https://whispering-badlands-86315.herokuapp.com/Parent/"
        var BASE_URL = "http://10.0.2.2:3000/"
        //var BASE_URL = "http://172.17.5.234:3000/"

        fun create() : ParentApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ParentApiInterface::class.java)


        }
    }
}