package com.example.challengechapter6.presentation.usecase.main.profile

import com.example.challengechapter6.data.remote.response.auth.GetUserResponse
import com.example.challengechapter6.domain.AccountRepository
import com.example.challengechapter6.domain.TokenRepository
import javax.inject.Inject

class GetDataUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(): GetUserResponse {
        return accountRepository.getDataUser(tokenRepository.getToken()!!)
    }
}