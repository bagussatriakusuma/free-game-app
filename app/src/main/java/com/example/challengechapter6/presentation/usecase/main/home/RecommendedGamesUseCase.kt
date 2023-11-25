package com.example.challengechapter6.presentation.usecase.main.home

import com.example.domain.MainRepository
import javax.inject.Inject

class RecommendedGamesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<com.example.data.remote.response.main.GetAllGamesResponse>{
        return mainRepository.getAllGames(platform = "all")
    }
}