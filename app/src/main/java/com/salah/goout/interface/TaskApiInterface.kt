package com.salah.goout.`interface`

import com.salah.goout.model.Task
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApiInterface {
    @GET("/parent/{id}/gettasks")
    fun gettask(@Path(value = "id") id: String): Call<List<Task>>


    companion object {

        var BASE_URL = "https://gooutapi.herokuapp.com/"


        fun create(): TaskApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(TaskApiInterface::class.java)


        }
    }
}