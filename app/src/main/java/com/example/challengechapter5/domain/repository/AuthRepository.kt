package com.example.challengechapter5.domain.repository

import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.data.remote.response.auth.LoginResponse

interface AuthRepository {
    suspend fun userLogin(request: LoginRequest): LoginResponse

    suspend fun getDataUser(token: String): GetUserResponse
}