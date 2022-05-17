package com.example.goout.model



data class Task(
    var _id:Long = 0,
    var title:String,
    var description:String,
    var category: String,
    var date:Long,
    var time:Long,
    var isFinished : Int = 0,


)