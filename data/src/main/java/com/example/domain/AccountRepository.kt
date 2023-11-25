package com.example.domain

import com.example.data.remote.response.auth.GetUserResponse
import com.example.data.remote.response.auth.UpdateUserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AccountRepository {
    suspend fun getDataUser(token: String): GetUserResponse

    suspend fun updateDataUser(
        token: String,
        picture: MultipartBody.Part?,
        name: RequestBody?,
        phoneNumber: RequestBody?,
        city: RequestBody?,
        address: RequestBody?
    ): UpdateUserResponse
}