package com.example.starwarsuniverseexplorer.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarsuniverseexplorer.viewmodel.PeopleViewModel
import com.example.starwarsuniverseexplorer.viewmodel.PersonDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.FilmDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.FilmsViewModel
import com.example.starwarsuniverseexplorer.viewmodel.PlanetsViewModel
import com.example.starwarsuniverseexplorer.viewmodel.StarshipsViewModel
import com.example.starwarsuniverseexplorer.viewmodel.VehiclesViewModel
import com.example.starwarsuniverseexplorer.viewmodel.PlanetDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.StarshipDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.VehicleDetailViewModel

object ViewModelFactories {

    val peopleFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PeopleViewModel(ServiceLocator.repository) as T
        }
    }
    val personDetailFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PersonDetailViewModel(ServiceLocator.repository) as T
        }
    }
    val filmsFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilmsViewModel(ServiceLocator.repository) as T
        }
    }

    val filmDetailFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilmDetailViewModel(ServiceLocator.repository) as T
        }
    }
    val planetsFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlanetsViewModel(ServiceLocator.repository) as T
        }
    }

    val starshipsFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StarshipsViewModel(ServiceLocator.repository) as T
        }
    }

    val vehiclesFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VehiclesViewModel(ServiceLocator.repository) as T
        }
    }

    val planetDetailFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlanetDetailViewModel(ServiceLocator.repository) as T
        }
    }

    val starshipDetailFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StarshipDetailViewModel(ServiceLocator.repository) as T
        }
    }

    val vehicleDetailFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VehicleDetailViewModel(ServiceLocator.repository) as T
        }
    }
}