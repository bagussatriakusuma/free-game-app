package com.example.challengechapter6.presentation.usecase.main.splashscreen

import com.example.challengechapter6.domain.TokenRepository
import javax.inject.Inject

class CheckLoggedInUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): Boolean{
        return tokenRepository.getToken().isNullOrEmpty()
    }
}