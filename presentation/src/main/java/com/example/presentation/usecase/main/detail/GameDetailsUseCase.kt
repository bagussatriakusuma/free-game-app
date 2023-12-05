package com.example.presentation.usecase.main.detail

import com.example.domain.model.main.DetailGames
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class GameDetailsUseCase @Inject constructor(
   private val mainRepository: MainRepository
) {
    suspend operator fun invoke(id: Int): DetailGames {
        return mainRepository.getDetailGames(id)
    }
}