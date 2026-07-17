package com.example.habittracker.database

import androidx.room.*
import com.example.habittracker.model.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getAllHabits(): List<Habit>

    @Query("SELECT * FROM habit WHERE id = :id")
    fun getHabitById(id: String): Habit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(habit: Habit)

    @Update
    fun updateHabit(habit: Habit)
}