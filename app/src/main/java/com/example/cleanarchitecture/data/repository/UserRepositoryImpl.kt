package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.api.GithubApi
import com.example.cleanarchitecture.data.database.UserDao
import com.example.cleanarchitecture.data.mapper.toData
import com.example.cleanarchitecture.data.mapper.toDomain
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository

class UserRepositoryImpl(
    private val githubApi: GithubApi,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUsersRemote(since: Int, perPage: Int): Result<List<User>> = runCatching {
        githubApi.getUsers(since, perPage)
            .map { it.toDomain() }
    }

    override suspend fun getUsersLocal(): List<User> {
        return userDao.getUsers().map { it.toDomain() }
    }

    override suspend fun saveUsers(users: List<User>) {
        userDao.insertUsers(users.map { it.toData() })
    }
}