package com.example.challengechapter6.presentation.usecase.main.home

import com.example.domain.MainRepository
import javax.inject.Inject

class PopularGamesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(platform: String, category: String): List<com.example.data.remote.response.main.GetAllGamesResponse> {
        return mainRepository.getGamesBySorted(platform, category)
    }
}