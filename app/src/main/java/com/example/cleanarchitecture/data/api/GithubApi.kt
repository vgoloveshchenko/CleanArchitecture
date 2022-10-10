package com.example.cleanarchitecture.data.api

import com.example.cleanarchitecture.data.model.UserDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): List<UserDTO>
}