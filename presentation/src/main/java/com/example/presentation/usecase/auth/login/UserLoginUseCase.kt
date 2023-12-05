package com.example.presentation.usecase.auth.login

import com.example.domain.model.auth.UserLoginRequest
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val insertTokenUseCase: InsertTokenUseCase
) {
    suspend operator fun invoke(request: UserLoginRequest): Boolean{
        val result = authRepository.userLogin(request)
        val token = result.token.orEmpty()
        insertTokenUseCase.invoke(token = token)
        return true
    }
}