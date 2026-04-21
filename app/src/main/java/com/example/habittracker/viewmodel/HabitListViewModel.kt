package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habittracker.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class HabitListViewModel(application: Application) : AndroidViewModel(application) {

    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val fileName = "habits.json"

    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false

        val habitList = readFromInternalStorage()

        habitsLD.value = habitList
        loadingLD.value = false
    }

    private fun readFromInternalStorage(): ArrayList<Habit> {
        val file = File(getApplication<Application>().filesDir, fileName)
        if (!file.exists()) return arrayListOf()

        return try {
            val jsonString = file.readText()
            val sType = object : TypeToken<List<Habit>>() {}.type
            Gson().fromJson<ArrayList<Habit>>(jsonString, sType) // Konversi JSON ke Object (Materi Week 4)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    fun addHabit(habit: Habit) {
        val currentList = readFromInternalStorage()
        currentList.add(habit)

        val jsonString = Gson().toJson(currentList)
        val file = File(getApplication<Application>().filesDir, fileName)
        file.writeText(jsonString)

        refresh()
    }
}