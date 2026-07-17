package com.example.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "habit_name")
    var title: String,
    var description: String,
    var goal: Int,
    var currentProgress: Int = 0,
    var unit: String,
    @ColumnInfo(name = "photo_url")
    var photoUrl: String? = null
)