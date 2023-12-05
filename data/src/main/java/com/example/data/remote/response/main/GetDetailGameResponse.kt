package com.example.data.remote.response.main

import com.example.domain.model.main.DetailGames
import com.example.domain.model.main.ScreenshotsGames
import com.google.gson.annotations.SerializedName

data class GetDetailGameResponse (

    @SerializedName("id"                          ) var id                        : Int?                       = null,
    @SerializedName("title"                       ) var title                     : String?                    = null,
    @SerializedName("thumbnail"                   ) var thumbnail                 : String?                    = null,
    @SerializedName("status"                      ) var status                    : String?                    = null,
    @SerializedName("short_description"           ) var shortDescription          : String?                    = null,
    @SerializedName("description"                 ) var description               : String?                    = null,
    @SerializedName("game_url"                    ) var gameUrl                   : String?                    = null,
    @SerializedName("genre"                       ) var genre                     : String?                    = null,
    @SerializedName("platform"                    ) var platform                  : String?                    = null,
    @SerializedName("publisher"                   ) var publisher                 : String?                    = null,
    @SerializedName("developer"                   ) var developer                 : String?                    = null,
    @SerializedName("release_date"                ) var releaseDate               : String?                    = null,
    @SerializedName("freetogame_profile_url"      ) var freetogameProfileUrl      : String?                    = null,
    @SerializedName("minimum_system_requirements" ) var minimumSystemRequirements : MinimumSystemRequirements? = MinimumSystemRequirements(),
    @SerializedName("screenshots"                 ) var screenshots               : ArrayList<Screenshots>     = arrayListOf()

) {
    data class MinimumSystemRequirements (

        @SerializedName("os"        ) var os        : String? = null,
        @SerializedName("processor" ) var processor : String? = null,
        @SerializedName("memory"    ) var memory    : String? = null,
        @SerializedName("graphics"  ) var graphics  : String? = null,
        @SerializedName("storage"   ) var storage   : String? = null

    )

    data class Screenshots (

        @SerializedName("id"    ) var id    : Int?    = null,
        @SerializedName("image" ) var image : String? = null

    )
}

fun GetDetailGameResponse.toDetailGames(): DetailGames {
    return DetailGames(
        id = id.hashCode(),
        title = title.orEmpty(),
        thumbnail = thumbnail.orEmpty(),
        status = status.orEmpty(),
        shortDescription = shortDescription.orEmpty(),
        description = description.orEmpty(),
        gameUrl = gameUrl.orEmpty(),
        genre = genre.orEmpty(),
        platform = platform.orEmpty(),
        publisher = publisher.orEmpty(),
        developer = developer.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        freetogameProfileUrl = freetogameProfileUrl.orEmpty(),
        screenshots = screenshots.toScreenshotsGamesList()
    )
}

fun List<GetDetailGameResponse.Screenshots>.toScreenshotsGamesList(): List<ScreenshotsGames> {
    return map { screenshot ->
        ScreenshotsGames(
            id = screenshot.id ?: 0,
            image = screenshot.image.orEmpty()
        )
    }
}