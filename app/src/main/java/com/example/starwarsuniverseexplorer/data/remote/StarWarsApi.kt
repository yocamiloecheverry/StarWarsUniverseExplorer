package com.example.starwarsuniverseexplorer.data.remote

import com.example.starwarsuniverseexplorer.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApi {

    // PEOPLE
    @GET("people/")
    suspend fun getPeople(
        @Query("search") search: String? = null
    ): PagedResponse<PersonDto>

    @GET("people/{id}/")
    suspend fun getPersonDetail(@Path("id") id: Int): PersonDto

    // FILMS
    @GET("films/")
    suspend fun getFilms(): PagedResponse<FilmDto>

    @GET("films/{id}/")
    suspend fun getFilmDetail(@Path("id") id: Int): FilmDto

    // PLANETS / STARSHIPS / VEHICLES
    @GET("planets/")
    suspend fun getPlanets(): PagedResponse<PlanetDto>

    @GET("starships/")
    suspend fun getStarships(): PagedResponse<StarshipDto>

    @GET("vehicles/")
    suspend fun getVehicles(): PagedResponse<VehicleDto>

    // Resolver URLs (para traer homeworld y films desde URL completa)
    @GET
    suspend fun getPlanetByUrl(@Url url: String): PlanetDto

    @GET
    suspend fun getFilmByUrl(@Url url: String): FilmDto

    @GET("planets/{id}/")
    suspend fun getPlanetDetail(@Path("id") id: Int): PlanetDto

    @GET("starships/{id}/")
    suspend fun getStarshipDetail(@Path("id") id: Int): StarshipDto

    @GET("vehicles/{id}/")
    suspend fun getVehicleDetail(@Path("id") id: Int): VehicleDto
}