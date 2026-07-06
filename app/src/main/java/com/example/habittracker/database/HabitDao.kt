package com.example.habittracker.database

import androidx.room.*
import com.example.habittracker.model.Habit

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    suspend fun getAllHabits(): List<Habit>

    @Query("SELECT * FROM habit WHERE id = :id")
    suspend fun getHabitById(id: String): Habit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)
}