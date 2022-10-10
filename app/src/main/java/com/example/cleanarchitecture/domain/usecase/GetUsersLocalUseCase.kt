package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository

class GetUsersLocalUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<User> {
        return userRepository.getUsersLocal()
    }
}