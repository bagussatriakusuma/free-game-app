package com.example.challengechapter6.presentation.usecase.main.profile

import com.example.challengechapter6.domain.AccountRepository
import com.example.challengechapter6.domain.TokenRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class UpdateDataUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(
        picture: File?,
        name: String,
        phoneNumber: String,
        city: String,
        address: String
    ){
        val requestFile = picture?.let { com.example.challengechapter6.common.reduceFileImage(it).asRequestBody("image/jpg".toMediaTypeOrNull()) }
        val imagePart = requestFile?.let { MultipartBody.Part.createFormData("picture", picture.name, it) }
        val nameRequest = name.toRequestBody("text/plain".toMediaType())
        val phoneNumberRequest = phoneNumber.toRequestBody("text/plain".toMediaType())
        val cityRequest = city.toRequestBody("text/plain".toMediaType())
        val addressRequest = address.toRequestBody("text/plain".toMediaType())

        accountRepository.updateDataUser(
            token = tokenRepository.getToken()!!,
            picture = imagePart,
            name = nameRequest,
            phoneNumber = phoneNumberRequest,
            city = cityRequest,
            address = addressRequest
        )
    }
}