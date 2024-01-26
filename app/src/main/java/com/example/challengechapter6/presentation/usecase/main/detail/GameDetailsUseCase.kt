package com.example.challengechapter6.presentation.usecase.main.detail

import com.example.challengechapter6.data.remote.response.main.GetDetailGameResponse
import com.example.challengechapter6.domain.MainRepository
import javax.inject.Inject

class GameDetailsUseCase @Inject constructor(
   private val mainRepository: MainRepository
) {
    suspend operator fun invoke(id: Int): GetDetailGameResponse {
        return mainRepository.getDetailGames(id)
    }
}