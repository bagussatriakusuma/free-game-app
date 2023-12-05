package com.example.presentation.usecase.main.profile

import com.example.domain.model.auth.UserData
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.TokenRepository
import javax.inject.Inject

class GetDataUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(): UserData {
        return accountRepository.getDataUser(tokenRepository.getToken()!!)
    }
}