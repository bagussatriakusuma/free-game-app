package com.example.presentation.usecase.main.home

import com.example.domain.model.main.AllGames
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class RecommendedGamesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<AllGames>{
        return mainRepository.getAllGames(platform = "all")
    }
}