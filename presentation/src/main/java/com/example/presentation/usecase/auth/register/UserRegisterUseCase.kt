package com.example.presentation.usecase.auth.register

import com.example.domain.model.auth.UserRegisterRequest
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(request: UserRegisterRequest): Boolean{
        authRepository.userRegister(request)
        return true
    }
}