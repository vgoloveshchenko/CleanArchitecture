package com.example.cleanarchitecture.data.di

import androidx.room.Room
import com.example.cleanarchitecture.data.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "database"
        ).build()
    }

    single { get<AppDatabase>().userDao }
}