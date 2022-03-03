package com.example.demo.network

class ApiHelper(private val apiServices: ApiServices) {

    suspend fun getUsersData() = apiServices.getUsers()
}