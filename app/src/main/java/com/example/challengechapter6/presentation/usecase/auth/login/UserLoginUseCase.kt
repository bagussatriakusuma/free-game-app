package com.example.challengechapter6.presentation.usecase.auth.login

import com.example.domain.AuthRepository
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val insertTokenUseCase: InsertTokenUseCase
) {
    suspend operator fun invoke(request: com.example.data.remote.request.auth.LoginRequest): Boolean{
        val result = authRepository.userLogin(request)
        val token = result.data?.token.orEmpty()
        insertTokenUseCase.invoke(token = token)
        return result.status == "success"
    }
}