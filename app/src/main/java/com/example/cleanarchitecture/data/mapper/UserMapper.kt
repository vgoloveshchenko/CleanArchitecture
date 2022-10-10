package com.example.cleanarchitecture.data.mapper

import com.example.cleanarchitecture.data.model.UserDTO
import com.example.cleanarchitecture.data.model.UserEntity
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.model.UserId

fun UserDTO.toDomain(): User {
    return User(
        id = UserId(id),
        login = login,
        avatarUrl = avatarUrl
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = UserId(id),
        login = login,
        avatarUrl = avatarUrl
    )
}

fun User.toData(): UserEntity {
    return UserEntity(
        id = id.rawId,
        login = login,
        avatarUrl = avatarUrl
    )
}