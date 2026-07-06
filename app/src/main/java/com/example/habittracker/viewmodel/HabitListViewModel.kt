package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habittracker.database.AppDatabase
import com.example.habittracker.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HabitListViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)

    val habitsLD = MutableLiveData<List<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        loadingLD.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val habitList = db.habitDao().getAllHabits()
            withContext(Dispatchers.Main) {
                habitsLD.value = habitList
                loadingLD.value = false
            }
        }
    }

    fun updateProgress(habitId: String, delta: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val habit = db.habitDao().getHabitById(habitId)
            if (habit != null) {
                habit.currentProgress = (habit.currentProgress + delta)
                    .coerceAtLeast(0)
                    .coerceAtMost(habit.goal)

                db.habitDao().updateHabit(habit)
                refresh()
            }
        }
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            db.habitDao().insertHabit(habit)
            refresh()
        }
    }
}