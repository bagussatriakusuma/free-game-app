package com.example.challengechapter6.presentation.usecase.auth.register

import com.example.data.remote.request.auth.RegisterRequest
import com.example.domain.AuthRepository
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(request: RegisterRequest): Boolean{
        val result = authRepository.userRegister(request)
        return result.status == "success"
    }
}