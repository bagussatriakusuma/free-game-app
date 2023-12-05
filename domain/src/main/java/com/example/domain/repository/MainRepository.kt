package com.example.domain.repository

import com.example.domain.model.main.AllGames
import com.example.domain.model.main.DetailGames

interface MainRepository {
    suspend fun getAllGames(platform: String): List<AllGames>

    suspend fun getGamesBySorted(platform: String, category: String): List<AllGames>

    suspend fun getDetailGames(id: Int): DetailGames
}