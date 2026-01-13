package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.StarshipDto
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StarshipDetailViewModel(
    private val repo: StarWarsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<StarshipDto>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<StarshipDto>> = _state

    fun load(id: Int) {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            try {
                _state.value = NetworkResult.Success(repo.getStarshipDetail(id))
            } catch (e: Exception) {
                _state.value = NetworkResult.Error(e.message ?: "Error cargando nave")
            }
        }
    }
}