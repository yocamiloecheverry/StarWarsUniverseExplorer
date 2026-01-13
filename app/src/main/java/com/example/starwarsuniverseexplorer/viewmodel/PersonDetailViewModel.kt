package com.example.starwarsuniverseexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsuniverseexplorer.data.model.FilmDto
import com.example.starwarsuniverseexplorer.data.model.PersonDto
import com.example.starwarsuniverseexplorer.data.model.PlanetDto
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.utils.mapError
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PersonDetailUi(
    val person: PersonDto,
    val planet: PlanetDto?,
    val films: List<FilmDto>
)

class PersonDetailViewModel(private val repo: StarWarsRepository) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<PersonDetailUi>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<PersonDetailUi>> = _state

    fun load(id: Int) {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            try {
                val person = repo.getPersonDetail(id)

                // Traer planeta y films en paralelo
                val planetDeferred = async { repo.getPlanetByUrl(person.homeworld) }
                val filmsDeferred = async {
                    person.films.map { filmUrl ->
                        async { repo.getFilmByUrl(filmUrl) }
                    }.map { it.await() }
                }

                val planet = planetDeferred.await()
                val films = filmsDeferred.await()

                _state.value = NetworkResult.Success(
                    PersonDetailUi(person = person, planet = planet, films = films)
                )
            } catch (e: Exception) {
                _state.value = NetworkResult.Error(mapError(e))
            }
        }
    }
}