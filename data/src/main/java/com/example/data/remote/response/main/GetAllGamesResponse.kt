package com.example.data.remote.response.main

import com.example.domain.model.main.AllGames
import com.google.gson.annotations.SerializedName

data class GetAllGamesResponse (

    @SerializedName("id"                     ) var id                   : Int?    = null,
    @SerializedName("title"                  ) var title                : String? = null,
    @SerializedName("thumbnail"              ) var thumbnail            : String? = null,
    @SerializedName("short_description"      ) var shortDescription     : String? = null,
    @SerializedName("game_url"               ) var gameUrl              : String? = null,
    @SerializedName("genre"                  ) var genre                : String? = null,
    @SerializedName("platform"               ) var platform             : String? = null,
    @SerializedName("publisher"              ) var publisher            : String? = null,
    @SerializedName("developer"              ) var developer            : String? = null,
    @SerializedName("release_date"           ) var releaseDate          : String? = null,
    @SerializedName("freetogame_profile_url" ) var freetogameProfileUrl : String? = null

)

fun GetAllGamesResponse.toAllGames(): AllGames {
    return AllGames(
        id = id.hashCode(),
        title = title.orEmpty(),
        thumbnail = thumbnail.orEmpty(),
        shortDescription = shortDescription.orEmpty(),
        gameUrl = gameUrl.orEmpty(),
        genre = genre.orEmpty(),
        platform = platform.orEmpty(),
        publisher = publisher.orEmpty(),
        developer = developer.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        freetogameProfileUrl = freetogameProfileUrl.orEmpty(),
    )
}
