package com.example.cleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)