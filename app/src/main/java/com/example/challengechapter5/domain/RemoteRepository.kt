package com.example.challengechapter5.domain

import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.data.remote.response.auth.LoginResponse
import com.example.challengechapter5.data.remote.service.AuthAPI
import com.example.challengechapter5.datastore.DatastoreManager
import com.example.challengechapter5.domain.repository.AuthRepository
import com.example.challengechapter5.domain.repository.TokenRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val datastore: DatastoreManager,
    private val api: AuthAPI
): TokenRepository, AuthRepository {
    override suspend fun setToken(token: String) {
        datastore.setToken(token)
    }

    override suspend fun getToken(): String? {
        return datastore.getToken().firstOrNull()
    }

    override suspend fun clearToken() {
        setToken("")
    }

    override suspend fun userLogin(request: LoginRequest): LoginResponse {
        return api.login(request)
    }

    override suspend fun getDataUser(token: String): GetUserResponse {
        return api.getUser(token = "Bearer $token")
    }
}