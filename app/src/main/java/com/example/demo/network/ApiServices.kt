package com.example.demo.network

import com.example.demo.data.Users
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    suspend fun getUsers(): List<Users>
}