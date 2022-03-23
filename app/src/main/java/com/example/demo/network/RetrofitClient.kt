package com.example.demo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val baseUrl = "http://192.168.1.16:3000/"

    private fun getRetrofitClient() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServices: ApiServices = getRetrofitClient().create(ApiServices::class.java)
}