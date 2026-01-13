package com.example.starwarsuniverseexplorer.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarsuniverseexplorer.data.remote.RetrofitClient
import com.example.starwarsuniverseexplorer.data.repository.StarWarsRepository
import com.example.starwarsuniverseexplorer.viewmodel.*

object AppFactory {

    private val repository by lazy {
        StarWarsRepository(RetrofitClient.api)
    }

    val peopleVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PeopleViewModel(repository) as T
        }
    }

    val personDetailVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PersonDetailViewModel(repository) as T
        }
    }

    val filmsVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilmsViewModel(repository) as T
        }
    }

    val filmDetailVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilmDetailViewModel(repository) as T
        }
    }

    val planetsVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlanetsViewModel(repository) as T
        }
    }

    val starshipsVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StarshipsViewModel(repository) as T
        }
    }
    val vehiclesVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VehiclesViewModel(repository) as T
        }
    }

    val planetDetailVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlanetDetailViewModel(ServiceLocator.repository) as T
        }
    }

    val starshipDetailVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StarshipDetailViewModel(ServiceLocator.repository) as T
        }
    }

    val vehicleDetailVMFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VehicleDetailViewModel(ServiceLocator.repository) as T
        }
    }
}