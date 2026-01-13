package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

data class VehicleDto(
    val name: String,
    val model: String,
    val manufacturer: String,
    val url: String
)