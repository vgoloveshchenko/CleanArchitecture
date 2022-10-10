package com.example.cleanarchitecture.data.di

import com.example.cleanarchitecture.data.repository.UserRepositoryImpl
import com.example.cleanarchitecture.domain.repository.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepositoryImpl) {
        bind<UserRepository>()
    }
}