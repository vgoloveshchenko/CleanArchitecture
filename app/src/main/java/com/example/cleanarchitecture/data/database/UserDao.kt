package com.example.cleanarchitecture.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.data.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    suspend fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM UserEntity")
    suspend fun clear()
}