package com.example.domain.model.auth

data class UserRegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
