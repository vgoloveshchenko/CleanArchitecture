package com.example.cleanarchitecture.data.di

import com.example.cleanarchitecture.data.api.GithubApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.github.com/"

val networkModule = module {
    single { OkHttpClient.Builder().build() }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create<GithubApi>() }
}