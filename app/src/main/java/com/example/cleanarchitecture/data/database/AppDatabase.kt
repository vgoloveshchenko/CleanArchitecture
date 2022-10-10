package com.example.cleanarchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}