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

class FilmDetailViewModel(private val repo: StarWarsRepository) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<FilmDto>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<FilmDto>> = _state

    fun load(id: Int) {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            try {
                _state.value = NetworkResult.Success(repo.getFilmDetail(id))
            } catch (e: Exception) {
                _state.value = NetworkResult.Error(mapError(e))
            }
        }
    }
}