package com.example.presentation.usecase.main.profile

import com.example.domain.repository.TokenRepository
import javax.inject.Inject

class ClearDataUserUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(){
        tokenRepository.clearToken()
    }
}