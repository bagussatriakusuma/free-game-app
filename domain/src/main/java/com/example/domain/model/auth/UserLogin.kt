package com.example.domain.model.auth

data class UserLogin(
    val id: Int,
    val name: String,
    val email: String,
    val token: String,
    val status: String
)
