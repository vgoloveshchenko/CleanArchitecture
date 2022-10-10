package com.example.cleanarchitecture

import android.app.Application
import com.example.cleanarchitecture.data.di.databaseModule
import com.example.cleanarchitecture.data.di.networkModule
import com.example.cleanarchitecture.data.di.repositoryModule
import com.example.cleanarchitecture.data.di.useCaseModule
import com.example.cleanarchitecture.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CleanArchitectureApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CleanArchitectureApp)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}