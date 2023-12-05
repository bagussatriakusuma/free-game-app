package com.example.presentation.usecase.main.splashscreen

import com.example.domain.repository.TokenRepository
import javax.inject.Inject

class CheckLoggedInUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): Boolean{
        return tokenRepository.getToken().isNullOrEmpty()
    }
}