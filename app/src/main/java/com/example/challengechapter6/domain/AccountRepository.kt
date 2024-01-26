package com.example.challengechapter6.domain

import com.example.challengechapter6.data.remote.response.auth.GetUserResponse
import com.example.challengechapter6.data.remote.response.auth.UpdateUserResponse
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