package com.example.challengechapter6.presentation.usecase.main.profile

import com.example.domain.TokenRepository
import javax.inject.Inject

class ClearDataUserUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(){
        tokenRepository.clearToken()
    }
}