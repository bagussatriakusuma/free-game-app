package com.example.domain.model.main

data class DetailGames(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val status: String,
    val shortDescription: String,
    val description: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val freetogameProfileUrl: String,
    val screenshots: List<ScreenshotsGames>
)
