package com.example.domain

interface TokenRepository {
    suspend fun setToken(token: String)

    suspend fun getToken(): String?

    suspend fun clearToken()
}