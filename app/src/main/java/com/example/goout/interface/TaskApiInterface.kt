package com.example.goout.`interface`

import com.example.goout.model.Task
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApiInterface {
    @GET("/parent/{id}/gettasks")
    fun gettask(@Path(value = "id") id: String): Call<List<Task>>


    companion object {

        //var BASE_URL = "https://whispering-badlands-86315.herokuapp.com/Parent/"
        var BASE_URL = "http://10.0.2.2:3000/"
        //var BASE_URL = "http://172.17.5.234:3000/"

        fun create(): TaskApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(TaskApiInterface::class.java)


        }
    }
}