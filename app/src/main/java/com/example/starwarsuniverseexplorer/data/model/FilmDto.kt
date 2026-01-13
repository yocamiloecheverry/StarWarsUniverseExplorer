package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

data class FilmDto(
    val title: String,
    @SerializedName("episode_id") val episodeId: Int,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("opening_crawl") val openingCrawl: String,
    val director: String,
    val producer: String,
    val characters: List<String>,
    val url: String
)