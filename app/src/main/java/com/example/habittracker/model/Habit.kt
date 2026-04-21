package com.example.habittracker.model

import com.google.gson.annotations.SerializedName

data class Habit(
    val id: String,
    @SerializedName("habit_name")
    val title: String,
    val description: String,
    val points: Int,
    @SerializedName("photo_url")
    val photoUrl: String? = null
)