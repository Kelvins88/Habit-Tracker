package com.example.habittracker.database

import androidx.room.*
import com.example.habittracker.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun login(username: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)
}