package com.salah.goout.`interface`

import com.salah.goout.model.Kid

import com.salah.goout.model.Parent
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

    @POST("/parent/RegisterParent")
    fun register(@Body() parent : Parent):Call<Parent>

    @POST("/parent/{id}/Registerkids")
            fun registerkid(@Path(value = "id") id:String,@Body kid: Kid) : Call<Kid>



    companion object {

        var BASE_URL = "https://gooutapi.herokuapp.com/"


        fun create() : ParentApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ParentApiInterface::class.java)


        }
    }
}