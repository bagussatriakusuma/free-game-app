package com.example.domain.model.auth

data class UserRegister(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val picture: String,
    val phoneNumber: String,
    val address: String,
    val city: String,
    val createdAt: String,
    val updatedAt: String
)
