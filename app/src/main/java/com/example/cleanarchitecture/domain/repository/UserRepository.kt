package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.model.User

interface UserRepository {

    suspend fun getUsersRemote(since: Int, perPage: Int): Result<List<User>>

    suspend fun getUsersLocal(): List<User>

    suspend fun saveUsers(users: List<User>)
}