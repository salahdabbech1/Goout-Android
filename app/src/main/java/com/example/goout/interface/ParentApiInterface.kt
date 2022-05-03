package com.example.goout.`interface`

import com.example.goout.model.Kid
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.goout.model.Parent
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface ParentApiInterface {
    @GET("/getall")
    fun getparents() : Call<List<Parent>>
    @GET("/{_id}/getKids")
    fun getkids(@Path(value = "_id") id: String): Call<List<Kid>>
    @POST("/parent/loginparent")
    fun login(@Body() parent : Parent): Call<Parent>
    @POST("/RegisterParent")
    fun register(@Body() parent : Parent):Call<Parent>



    companion object {

        //var BASE_URL = "https://whispering-badlands-86315.herokuapp.com/Parent/"
        var BASE_URL = "http://10.0.2.2:3000/"

        fun create() : ParentApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ParentApiInterface::class.java)


        }
    }
}