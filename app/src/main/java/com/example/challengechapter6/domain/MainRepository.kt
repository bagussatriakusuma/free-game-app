package com.example.challengechapter6.domain

import com.example.challengechapter6.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter6.data.remote.response.main.GetDetailGameResponse

interface MainRepository {
    suspend fun getAllGames(platform: String): List<GetAllGamesResponse>

    suspend fun getGamesBySorted(platform: String, category: String): List<GetAllGamesResponse>

    suspend fun getDetailGames(id: Int): GetDetailGameResponse
}