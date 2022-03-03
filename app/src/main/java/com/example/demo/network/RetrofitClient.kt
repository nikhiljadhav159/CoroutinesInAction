package com.example.demo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val baseUrl = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofitClient() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServices: ApiServices = getRetrofitClient().create(ApiServices::class.java)
}