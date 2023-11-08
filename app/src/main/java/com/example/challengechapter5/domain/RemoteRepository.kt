package com.example.challengechapter5.domain

import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.data.remote.request.auth.RegisterRequest
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.data.remote.response.auth.LoginResponse
import com.example.challengechapter5.data.remote.response.auth.RegisterResponse
import com.example.challengechapter5.data.remote.response.auth.UpdateUserResponse
import com.example.challengechapter5.data.remote.service.AuthAPI
import com.example.challengechapter5.datastore.DatastoreManager
import com.example.challengechapter5.domain.repository.AuthRepository
import com.example.challengechapter5.domain.repository.TokenRepository
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
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

    override suspend fun userRegister(request: RegisterRequest): RegisterResponse {
        return api.register(request)
    }

    override suspend fun getDataUser(token: String): GetUserResponse {
        return api.getUser2(token = "Bearer $token")
    }

    override suspend fun updateDataUser(
        token: String,
        image: MultipartBody.Part?,
        fullName: RequestBody?,
        phoneNumber: RequestBody?,
        address: RequestBody?,
        city: RequestBody?
    ): UpdateUserResponse {
        return api.updateUser(token = "Bearer $token", image, fullName, phoneNumber, address, city)
    }
}