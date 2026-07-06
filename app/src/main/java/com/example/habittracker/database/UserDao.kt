package com.example.habittracker.database

import androidx.room.*
import com.example.habittracker.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)
}