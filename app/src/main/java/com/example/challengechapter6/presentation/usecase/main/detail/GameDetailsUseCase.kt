package com.example.challengechapter6.presentation.usecase.main.detail

import com.example.domain.MainRepository
import javax.inject.Inject

class GameDetailsUseCase @Inject constructor(
   private val mainRepository: MainRepository
) {
    suspend operator fun invoke(id: Int): com.example.data.remote.response.main.GetDetailGameResponse {
        return mainRepository.getDetailGames(id)
    }
}