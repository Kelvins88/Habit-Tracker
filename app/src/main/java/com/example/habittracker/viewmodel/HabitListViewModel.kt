package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habittracker.database.AppDatabase
import com.example.habittracker.model.Habit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HabitListViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    private val db = AppDatabase.getDatabase(application)
    val habitsLD = MutableLiveData<List<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.postValue(true)
        launch {
            val habitList = db.habitDao().getAllHabits()
            habitsLD.postValue(habitList)
            loadingLD.postValue(false)
        }
    }

    fun updateProgress(habitId: String, delta: Int) {
        launch {
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
        launch {
            db.habitDao().insertHabit(habit)
            refresh()
        }
    }
}