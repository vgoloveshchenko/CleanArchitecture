package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository

class SaveUsersUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(users: List<User>) {
        userRepository.saveUsers(users)
    }
}