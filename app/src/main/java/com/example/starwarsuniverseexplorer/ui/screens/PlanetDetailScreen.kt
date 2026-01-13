package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsuniverseexplorer.ui.components.ErrorView
import com.example.starwarsuniverseexplorer.ui.components.LoadingView
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.viewmodel.PlanetDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreen(id: Int, vm: PlanetDetailViewModel, onBack: () -> Unit) {
    val state by vm.state.collectAsState()

    LaunchedEffect(id) { vm.load(id) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Detalle planeta") },
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
                val p = (state as NetworkResult.Success).data
                Column(
                    modifier = Modifier.padding(padding).padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(p.name, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
                    Text("Clima: ${p.climate}")
                    //Text("Terreno: ${p.terrain}")
                    //Text("Gravedad: ${p.gravity}")
                    Text("Población: ${p.population}")
                    //Text("Diámetro: ${p.diameter}")
                }
            }
        }
    }
}
