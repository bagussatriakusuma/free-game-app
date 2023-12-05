package com.example.domain.repository

import com.example.domain.model.auth.UpdateUser
import com.example.domain.model.auth.UserData
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AccountRepository {
    suspend fun getDataUser(token: String): UserData

    suspend fun updateDataUser(
        token: String,
        picture: MultipartBody.Part?,
        name: RequestBody?,
        phoneNumber: RequestBody?,
        city: RequestBody?,
        address: RequestBody?
    ): UpdateUser
}