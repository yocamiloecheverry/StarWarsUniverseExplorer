package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsuniverseexplorer.ui.components.ErrorView
import com.example.starwarsuniverseexplorer.ui.components.LoadingView
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.viewmodel.PersonDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailScreen(
    id: Int,
    vm: PersonDetailViewModel,
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(id) {
        vm.load(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle personaje") },
                navigationIcon = { TextButton(onClick = onBack) { Text("←") } },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        when (state) {
            is NetworkResult.Loading -> LoadingView(Modifier.padding(padding))
            is NetworkResult.Error -> ErrorView(
                message = (state as NetworkResult.Error).message,
                onRetry = { vm.load(id) },
                modifier = Modifier.padding(padding)
            )
            is NetworkResult.Success -> {
                val ui = (state as NetworkResult.Success).data
                val person = ui.person
                val planet = ui.planet
                val films = ui.films

                Column(
                    modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(person.name, style = MaterialTheme.typography.headlineMedium)

                    Text("Altura: ${person.height}")
                    Text("Peso: ${person.mass}")
                    Text("Género: ${person.gender}")
                    Text("Planeta natal: ${planet?.name ?: "Desconocido"}")

                    Spacer(Modifier.height(8.dp))
                    Text("Películas", style = MaterialTheme.typography.titleLarge)
                    films.forEach { film ->
                        Text("• ${film.title}")
                    }
                }
            }
        }
    }
}