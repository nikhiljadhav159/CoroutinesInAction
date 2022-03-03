package com.example.demo.data

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("username")
    val image: String,
    @SerializedName("email")
    val userEmail: String,
    @SerializedName("id")
    val userId: String,
    @SerializedName("name")
    val userName: String
)
