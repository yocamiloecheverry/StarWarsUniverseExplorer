package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.PlanetDto
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlanetDetailViewModel(
    private val repo: StarWarsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<PlanetDto>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<PlanetDto>> = _state

    fun load(id: Int) {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            try {
                _state.value = NetworkResult.Success(repo.getPlanetDetail(id))
            } catch (e: Exception) {
                _state.value = NetworkResult.Error(e.message ?: "Error cargando planeta")
            }
        }
    }
}