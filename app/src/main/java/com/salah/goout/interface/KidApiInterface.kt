package com.salah.goout.`interface`

import com.salah.goout.model.Kid
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface KidApiInterface {
    @POST("/parent/loginkid")
    fun loginkid(@Body kid : Kid): Call<Kid>

    companion object {

        var BASE_URL = "https://gooutapi.herokuapp.com/"
        //var BASE_URL = "http://10.0.2.2:3000/"
        //var BASE_URL = "http://172.17.5.234:3000/"

        fun create() : KidApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(KidApiInterface::class.java)
        }
    }

}
