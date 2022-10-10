package com.example.cleanarchitecture.data.di

import com.example.cleanarchitecture.domain.usecase.GetUsersLocalUseCase
import com.example.cleanarchitecture.domain.usecase.GetUsersUseCase
import com.example.cleanarchitecture.domain.usecase.SaveUsersUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::GetUsersUseCase)
    singleOf(::GetUsersLocalUseCase)
    singleOf(::SaveUsersUseCase)
}