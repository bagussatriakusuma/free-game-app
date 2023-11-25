package com.example.challengechapter6.presentation.usecase.auth.login

import com.example.domain.TokenRepository
import javax.inject.Inject

class InsertTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
){
    suspend operator fun invoke(token: String){
        tokenRepository.setToken(token)
    }
}