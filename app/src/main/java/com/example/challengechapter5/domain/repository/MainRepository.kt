package com.example.challengechapter5.domain.repository

import com.example.challengechapter5.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter5.data.remote.response.main.GetDetailGameResponse

interface MainRepository {
    suspend fun getAllGames(platform: String): List<GetAllGamesResponse>

    suspend fun getGamesBySorted(platform: String, category: String): List<GetAllGamesResponse>

    suspend fun getDetailGames(id: Int): GetDetailGameResponse
}