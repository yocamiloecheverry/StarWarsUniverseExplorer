package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.PersonDto
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.utils.mapError
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PeopleViewModel(private val repo: StarWarsRepository) : ViewModel() {

    private val _peopleState = MutableStateFlow<NetworkResult<List<PersonDto>>>(NetworkResult.Loading)
    val peopleState: StateFlow<NetworkResult<List<PersonDto>>> = _peopleState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private var searchJob: Job? = null

    init {
        fetchPeople(search = null)
    }

    fun onQueryChange(newValue: String) {
        _query.value = newValue

        // Debounce: espera 350ms antes de buscar, para no spamear la API
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(350)
            val q = _query.value.trim()
            fetchPeople(search = if (q.isBlank()) null else q)
        }
    }

    fun retry() {
        val q = _query.value.trim()
        fetchPeople(search = if (q.isBlank()) null else q)
    }

    private fun fetchPeople(search: String?) {
        viewModelScope.launch {
            _peopleState.value = NetworkResult.Loading
            try {
                val response = repo.getPeople(search)
                _peopleState.value = NetworkResult.Success(response.results)
            } catch (e: Exception) {
                _peopleState.value = NetworkResult.Error(
                    mapError(e)
                )
            }
        }
    }
}