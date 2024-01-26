package com.example.challengechapter6.domain

import com.example.challengechapter6.data.remote.request.auth.LoginRequest
import com.example.challengechapter6.data.remote.request.auth.RegisterRequest
import com.example.challengechapter6.data.remote.response.auth.LoginResponse
import com.example.challengechapter6.data.remote.response.auth.RegisterResponse

interface AuthRepository {
    suspend fun userLogin(request: LoginRequest): LoginResponse

    suspend fun userRegister(request: RegisterRequest): RegisterResponse
}