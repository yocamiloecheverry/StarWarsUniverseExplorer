package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsuniverseexplorer.ui.components.ErrorView
import com.example.starwarsuniverseexplorer.ui.components.LoadingView
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.viewmodel.FilmDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(
    id: Int,
    vm: FilmDetailViewModel,
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(id) { vm.load(id) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle película") },
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
                val film = (state as NetworkResult.Success).data
                Column(
                    modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(film.title, style = MaterialTheme.typography.headlineMedium)
                    Text("Episodio: ${film.episodeId}")
                    Text("Estreno: ${film.releaseDate}")
                    Text("Director: ${film.director}")
                    Text("Productor: ${film.producer}")

                    Spacer(Modifier.height(8.dp))
                    Text("Opening Crawl", style = MaterialTheme.typography.titleLarge)
                    Text(film.openingCrawl)
                }
            }
        }
    }
}