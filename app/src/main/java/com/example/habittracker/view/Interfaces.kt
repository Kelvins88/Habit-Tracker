package com.example.habittracker.view

import android.view.View
import com.example.habittracker.model.Habit

interface HabitCardListener {
    fun onEditClick(v: View, habit: Habit)
    fun onProgressUpdate(habit: Habit, delta: Int)
}

interface EditHabitListener {
    fun onSaveClick(v: View)
}