package com.example.cleanarchitecture.presentation.di

import com.example.cleanarchitecture.presentation.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::UsersViewModel)
}