package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository

class GetUsersUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(since: Int, perPage: Int): Result<List<User>> =
        userRepository.getUsersRemote(since, perPage)
}