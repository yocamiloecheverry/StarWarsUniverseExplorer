package com.example.starwarsuniverseexplorer.utils

import com.example.starwarsuniverseexplorer.data.remote.RetrofitClient
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository

object ServiceLocator {
    val repository: StarWarsRepository by lazy {
        StarWarsRepository(RetrofitClient.api)
    }
}