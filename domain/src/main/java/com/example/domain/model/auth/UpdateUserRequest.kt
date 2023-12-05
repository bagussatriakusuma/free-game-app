package com.example.domain.model.auth

data class UpdateUserRequest(
    val name: String,
    val picture: String,
    val phoneNumber: String,
    val city: String,
    val address: String,
)
