package com.example.cleanarchitecture.presentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.usecase.GetUsersLocalUseCase
import com.example.cleanarchitecture.domain.usecase.GetUsersUseCase
import com.example.cleanarchitecture.domain.usecase.SaveUsersUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersLocalUseCase: GetUsersLocalUseCase,
    private val saveUsersUseCase: SaveUsersUseCase
) : ViewModel() {

    private val pagingFlow = MutableSharedFlow<Unit>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val refreshFlow = MutableSharedFlow<Unit>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private var currentPage = 0
    private var isLoading = false

    val dataFlow = refreshFlow
        .onStart { emit(Unit) }
        .onEach {
            isLoading = false
            currentPage = 0
        }
        .flatMapLatest { pagingFlow() }
        .onStart {
            val localUsers = getUsersLocalUseCase()
            if (localUsers.isNotEmpty()) {
                emit(localUsers)
            } else {
                emit(emptyList())
            }
        }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            replay = 1
        )

    fun onLoadMore() {
        pagingFlow.tryEmit(Unit)
    }

    fun onRefreshed() {
        refreshFlow.tryEmit(Unit)
    }

    private fun pagingFlow(): Flow<List<User>> {
        return pagingFlow
            .onStart { emit(Unit) }
            .filter { !isLoading }
            .onEach { isLoading = true }
            .map {
                getUsersUseCase(currentPage * PAGE_SIZE, PAGE_SIZE)
                    .onSuccess {
                        saveUsersUseCase(it)
                        currentPage++
                    }
                    .fold(
                        onSuccess = { it },
                        onFailure = { emptyList() }
                    )
            }
            .onEach { isLoading = false }
            .runningReduce { accumulator, value -> accumulator + value }
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}