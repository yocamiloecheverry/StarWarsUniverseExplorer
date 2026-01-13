package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.FilmDto
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.utils.mapError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmsViewModel(private val repo: StarWarsRepository) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<List<FilmDto>>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<List<FilmDto>>> = _state

    init { loadFilms() }

    fun retry() = loadFilms()

    private fun loadFilms() {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            try {
                val response = repo.getFilms()
                _state.value = NetworkResult.Success(response.results)
            } catch (e: Exception) {
                _state.value = NetworkResult.Error(mapError(e))
            }
        }
    }
}