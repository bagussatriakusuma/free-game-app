package com.example.challengechapter6.presentation.usecase.main.home

import com.example.challengechapter6.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter6.domain.MainRepository
import javax.inject.Inject

class PopularGamesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(platform: String, category: String): List<GetAllGamesResponse> {
        return mainRepository.getGamesBySorted(platform, category)
    }
}