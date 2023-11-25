package com.example.challengechapter6.presentation.usecase.main.profile

import com.example.domain.AccountRepository
import com.example.domain.TokenRepository
import javax.inject.Inject

class GetDataUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(): com.example.data.remote.response.auth.GetUserResponse {
        return accountRepository.getDataUser(tokenRepository.getToken()!!)
    }
}