package com.example.fragment_tabs.data

import com.example.fragment_tabs.users.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers() : List<User>
}