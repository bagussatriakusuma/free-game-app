package com.example.presentation.usecase.main.home

import com.example.domain.model.main.AllGames
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class PopularGamesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(platform: String, category: String): List<AllGames> {
        return mainRepository.getGamesBySorted(platform, category)
    }
}