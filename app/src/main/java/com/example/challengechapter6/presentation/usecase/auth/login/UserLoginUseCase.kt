package com.example.challengechapter6.presentation.usecase.auth.login

import com.example.challengechapter6.data.remote.request.auth.LoginRequest
import com.example.challengechapter6.domain.AuthRepository
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val insertTokenUseCase: InsertTokenUseCase
) {
    suspend operator fun invoke(request: LoginRequest): Boolean{
        val result = authRepository.userLogin(request)
        val token = result.data?.token.orEmpty()
        insertTokenUseCase.invoke(token = token)
        return result.status == "success"
    }
}