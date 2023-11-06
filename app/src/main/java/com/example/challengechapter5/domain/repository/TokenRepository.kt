package com.example.challengechapter5.domain.repository

interface TokenRepository {
    suspend fun setToken(token: String)

    suspend fun getToken(): String?

    suspend fun clearToken()
}