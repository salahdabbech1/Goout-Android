package com.example.goout.model
import com.example.goout.model.Kid
data class Parent(var _id: String? = null, var Name:String?= null, var Email:String?= null,var Password :String? = null, var Picture : String? = null, var Kids: List<Kid>?= null, var Role :String?= null)