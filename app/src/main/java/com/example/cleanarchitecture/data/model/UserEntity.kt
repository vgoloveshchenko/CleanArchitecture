package com.example.cleanarchitecture.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val login: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String
)