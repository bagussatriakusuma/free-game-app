package com.example.domain

import com.example.data.remote.request.auth.LoginRequest
import com.example.data.remote.request.auth.RegisterRequest
import com.example.data.remote.response.auth.LoginResponse
import com.example.data.remote.response.auth.RegisterResponse

interface AuthRepository {
    suspend fun userLogin(request: LoginRequest): LoginResponse

    suspend fun userRegister(request: RegisterRequest): RegisterResponse
}