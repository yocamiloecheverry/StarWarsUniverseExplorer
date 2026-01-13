package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.StarshipDto
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.utils.mapError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StarshipsViewModel(private val repo: StarWarsRepository) : ViewModel() {

    // 4 Usages
    private val _state = MutableStateFlow<NetworkResult<List<StarshipDto>>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<List<StarshipDto>>> = _state

    init { load() }

    fun retry() = load()

    // 2 Usages
    private fun load() {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            try {
                _state.value = NetworkResult.Success(data = repo.getStarships().results)
            } catch (e: Exception) {
                _state.value = NetworkResult.Error(mapError(e))
            }
        }
    }
}