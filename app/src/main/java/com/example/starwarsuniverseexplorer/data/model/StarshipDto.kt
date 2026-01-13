package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

data class StarshipDto(
    val name: String,
    val model: String,
    val manufacturer: String,
    val url: String
)