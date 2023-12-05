package com.example.domain.repository

import com.example.domain.model.auth.UserLogin
import com.example.domain.model.auth.UserLoginRequest
import com.example.domain.model.auth.UserRegister
import com.example.domain.model.auth.UserRegisterRequest

interface AuthRepository {
    suspend fun userLogin(request: UserLoginRequest): UserLogin

    suspend fun userRegister(request: UserRegisterRequest): UserRegister
}