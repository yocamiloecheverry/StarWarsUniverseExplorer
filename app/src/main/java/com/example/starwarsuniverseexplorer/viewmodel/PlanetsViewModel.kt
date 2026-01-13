package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.PlanetDto
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.utils.mapError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlanetsViewModel(private val repo: StarWarsRepository) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<List<PlanetDto>>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<List<PlanetDto>>> = _state

    init { load() }

    fun retry() = load()

    private fun load() {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            try {
                _state.value = NetworkResult.Success(repo.getPlanets().results)
            } catch (e: Exception) {
                _state.value = NetworkResult.Error(mapError(e))
            }
        }
    }
}