package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habittracker.database.AppDatabase
import com.example.habittracker.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditHabitViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    val habitLD = MutableLiveData<Habit?>()

    fun fetch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val habit = db.habitDao().getHabitById(id)
            habitLD.postValue(habit)
        }
    }

    fun update(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            db.habitDao().updateHabit(habit)
        }
    }
}