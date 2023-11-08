package com.example.challengechapter5.domain.repository

import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.data.remote.request.auth.RegisterRequest
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.data.remote.response.auth.LoginResponse
import com.example.challengechapter5.data.remote.response.auth.RegisterResponse
import com.example.challengechapter5.data.remote.response.auth.UpdateUserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface AuthRepository {
    suspend fun userLogin(request: LoginRequest): LoginResponse

    suspend fun userRegister(request: RegisterRequest): RegisterResponse

    suspend fun getDataUser(token: String): GetUserResponse

    suspend fun updateDataUser(
        token: String,
        image: MultipartBody.Part?,
        fullName: RequestBody?,
        phoneNumber: RequestBody?,
        address: RequestBody?,
        city: RequestBody?
    ): UpdateUserResponse
}