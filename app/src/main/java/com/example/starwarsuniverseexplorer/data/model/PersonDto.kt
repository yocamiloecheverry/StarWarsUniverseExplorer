package com.example.starwarsuniverseexplorer.data.model

import com.google.gson.annotations.SerializedName

data class PersonDto(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String,
    val homeworld: String,          // URL del planeta natal
    val films: List<String>,        // URLs de films
    val url: String                 // URL del recurso (para obtener id)
)