package com.example.goout.model
import com.example.goout.model.Kid
data class Parent(
    val _id: String? = null,
    var Name:String?= null,
    var Email:String?= null,
    var Password :String? = null,
    val Picture : String? = null,
    val Kids:List<Any>?=null,
    val Role :String?= null)