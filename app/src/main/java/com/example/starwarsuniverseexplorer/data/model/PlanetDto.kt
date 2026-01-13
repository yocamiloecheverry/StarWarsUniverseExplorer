package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

data class PlanetDto(
    val name: String,
    val climate: String,
    val population: String,
    val url: String
)