package com.example.data.remote.service

import com.example.data.remote.response.main.GetAllGamesResponse
import com.example.data.remote.response.main.GetDetailGameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {
    @GET("games")
    suspend fun allGames(
        @Query("platform")platform: String
    ): List<GetAllGamesResponse>
    @GET("games")
    suspend fun gamesBySorted(
        @Query("platform")platform: String,
        @Query("category")category: String
    ): List<GetAllGamesResponse>

    @GET("game")
    suspend fun detailGame(
        @Query("id")id: Int
    ): GetDetailGameResponse
}