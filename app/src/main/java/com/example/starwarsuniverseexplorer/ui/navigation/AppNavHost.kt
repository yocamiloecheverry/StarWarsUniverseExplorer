package com.example.starwarsuniverseexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarsuniverseexplorer.ui.screens.*
import com.example.starwarsuniverseexplorer.ui.screens.HomeScreen
import com.example.starwarsuniverseexplorer.utils.AppFactory
import com.example.starwarsuniverseexplorer.ui.screens.FilmsListScreen
import com.example.starwarsuniverseexplorer.ui.screens.FilmDetailScreen
import com.example.starwarsuniverseexplorer.utils.ViewModelFactories
import com.example.starwarsuniverseexplorer.ui.screens.PlanetsListScreen
import com.example.starwarsuniverseexplorer.ui.screens.StarshipsListScreen
import com.example.starwarsuniverseexplorer.ui.screens.VehiclesListScreen
import com.example.starwarsuniverseexplorer.utils.extractIdFromUrl
import com.example.starwarsuniverseexplorer.viewmodel.PlanetDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.PlanetsViewModel
import com.example.starwarsuniverseexplorer.viewmodel.StarshipDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.StarshipsViewModel
import com.example.starwarsuniverseexplorer.viewmodel.VehicleDetailViewModel
import com.example.starwarsuniverseexplorer.viewmodel.VehiclesViewModel
import com.example.starwarsuniverseexplorer.ui.screens.PlanetDetailScreen
import com.example.starwarsuniverseexplorer.ui.screens.StarshipDetailScreen
import com.example.starwarsuniverseexplorer.ui.screens.VehicleDetailScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onPeople = { navController.navigate(Routes.PEOPLE) },
                onFilms = { navController.navigate(Routes.FILMS) },
                onPlanets = { navController.navigate(Routes.PLANETS) },
                onStarships = { navController.navigate(Routes.STARSHIPS) },
                onVehicles = { navController.navigate(Routes.VEHICLES) }
            )
        }

        composable(Routes.PEOPLE) {
            val vm = viewModel<com.example.starwarsuniverseexplorer.viewmodel.PeopleViewModel>(
                factory = AppFactory.peopleVMFactory
            )
            PeopleListScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                onPersonClick = { personUrl ->
                    val id = extractIdFromUrl(personUrl)
                    if (id > 0) {
                        navController.navigate("${Routes.PERSON_DETAIL}/$id")
                    } else {

                    }
                }
            )
        }

        composable(
            route = "${Routes.PERSON_DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1

            if (id <= 0) {
                navController.popBackStack()
                return@composable
            }

            val vm = viewModel<com.example.starwarsuniverseexplorer.viewmodel.PersonDetailViewModel>(
                factory = AppFactory.personDetailVMFactory
            )

            PersonDetailScreen(
                id = id,
                vm = vm,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.FILMS) {
            val vm = viewModel<com.example.starwarsuniverseexplorer.viewmodel.FilmsViewModel>(
                factory = AppFactory.filmsVMFactory
            )
            FilmsListScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                onFilmClick = { id ->
                    navController.navigate("${Routes.FILM_DETAIL}/$id")
                }
            )
        }
        composable(Routes.PLANETS) {
            val vm: PlanetsViewModel = viewModel(factory = ViewModelFactories.planetsFactory)
            PlanetsListScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                onPlanetClick = { id -> navController.navigate("${Routes.PLANET_DETAIL}/$id") }
            )
        }

        composable(Routes.STARSHIPS) {
            val vm: StarshipsViewModel = viewModel(factory = ViewModelFactories.starshipsFactory)
            StarshipsListScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                onStarshipClick = { id -> navController.navigate("${Routes.STARSHIP_DETAIL}/$id") }
            )
        }

        composable(Routes.VEHICLES) {
            val vm: VehiclesViewModel = viewModel(factory = ViewModelFactories.vehiclesFactory)
            VehiclesListScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                onVehicleClick = { id -> navController.navigate("${Routes.VEHICLE_DETAIL}/$id") }
            )
        }

        composable(
            route = "${Routes.FILM_DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            if (id <= 0) { navController.popBackStack(); return@composable }
            val vm = viewModel<com.example.starwarsuniverseexplorer.viewmodel.FilmDetailViewModel>(
                factory = AppFactory.filmDetailVMFactory
            )
            FilmDetailScreen(id = id, vm = vm, onBack = { navController.popBackStack() })
        }

        composable("${Routes.PLANET_DETAIL}/{id}", arguments = listOf(navArgument("id"){ type = NavType.IntType })) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            if (id <= 0) { navController.popBackStack(); return@composable }
            val vm = viewModel<PlanetDetailViewModel>(factory = AppFactory.planetDetailVMFactory)
            PlanetDetailScreen(id = id, vm = vm, onBack = { navController.popBackStack() })
        }

        composable(
            route = "${Routes.STARSHIP_DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            if (id <= 0) { navController.popBackStack(); return@composable }

            val vm: StarshipDetailViewModel = viewModel(factory = ViewModelFactories.starshipDetailFactory)
            StarshipDetailScreen(id = id, vm = vm, onBack = { navController.popBackStack() })
        }

        composable(
            route = "${Routes.VEHICLE_DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            if (id <= 0) { navController.popBackStack(); return@composable }

            val vm: VehicleDetailViewModel = viewModel(factory = ViewModelFactories.vehicleDetailFactory)
            VehicleDetailScreen(id = id, vm = vm, onBack = { navController.popBackStack() })
        }

    }
}