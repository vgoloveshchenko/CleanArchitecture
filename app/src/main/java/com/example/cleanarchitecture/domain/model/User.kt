package com.example.cleanarchitecture.domain.model

@JvmInline
value class UserId(val rawId: Long)

data class User(
    val id: UserId,
    val login: String,
    val avatarUrl: String
)