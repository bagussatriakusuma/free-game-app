package com.example.domain.model.auth

data class UserLoginRequest(
    val email: String,
    val password: String
)
