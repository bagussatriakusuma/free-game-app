package com.example.data.remote.service

import com.example.data.remote.response.auth.GetUserResponse
import com.example.data.remote.response.auth.LoginResponse
import com.example.data.remote.response.auth.RegisterResponse
import com.example.data.remote.response.auth.UpdateUserResponse
import com.example.domain.model.auth.UserLoginRequest
import com.example.domain.model.auth.UserRegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface AuthAPI {
    @POST("auth/login")
    suspend fun login(
        @Body request: UserLoginRequest
    ): LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body request: UserRegisterRequest
    ): RegisterResponse

    @GET("auth/user")
    suspend fun getUser(
        @Header("Authorization")token: String
    ): GetUserResponse

    @Multipart
    @PUT("auth/user")
    suspend fun updateUser(
        @Header("Authorization")token: String,
        @Part file: MultipartBody.Part? = null,
        @Part("name") name: RequestBody?,
        @Part("phone_number") phoneNumber: RequestBody?,
        @Part("city") city: RequestBody?,
        @Part("address") address: RequestBody?
    ): UpdateUserResponse
}