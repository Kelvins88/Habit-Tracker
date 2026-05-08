package com.example.habittracker.model

import com.google.gson.annotations.SerializedName

data class Habit(
    val id: String,
    @SerializedName("habit_name")
    val title: String,
    val description: String,
    val goal: Int,
    var currentProgress: Int = 0,
    val unit: String,
    @SerializedName("photo_url")
    val photoUrl: String? = null
)