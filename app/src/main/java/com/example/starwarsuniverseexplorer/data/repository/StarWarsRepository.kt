package com.example.starwarsuniverseexplorer.data.repository

import com.example.starwarsuniverseexplorer.data.model.*
import com.example.starwarsuniverseexplorer.data.remote.StarWarsApi

class StarWarsRepository(private val api: StarWarsApi) {

    suspend fun getPeople(search: String?): PagedResponse<PersonDto> =
        api.getPeople(search = search)

    suspend fun getPersonDetail(id: Int): PersonDto =
        api.getPersonDetail(id)

    suspend fun getFilms(): PagedResponse<FilmDto> =
        api.getFilms()

    suspend fun getFilmDetail(id: Int): FilmDto =
        api.getFilmDetail(id)

    suspend fun getPlanets(): PagedResponse<PlanetDto> =
        api.getPlanets()

    suspend fun getStarships(): PagedResponse<StarshipDto> =
        api.getStarships()

    suspend fun getVehicles(): PagedResponse<VehicleDto> =
        api.getVehicles()

    suspend fun getPlanetByUrl(url: String): PlanetDto =
        api.getPlanetByUrl(url)

    suspend fun getFilmByUrl(url: String): FilmDto =
        api.getFilmByUrl(url)

    suspend fun getPlanetDetail(id: Int) =
        api.getPlanetDetail(id)

    suspend fun getStarshipDetail(id: Int) =
        api.getStarshipDetail(id)

    suspend fun getVehicleDetail(id: Int) =
        api.getVehicleDetail(id)
}
