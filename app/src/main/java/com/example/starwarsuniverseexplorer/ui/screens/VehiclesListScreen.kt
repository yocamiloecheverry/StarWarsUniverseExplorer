package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.starwarsuniverseexplorer.ui.components.ErrorView
import com.example.starwarsuniverseexplorer.ui.components.LoadingView
import com.example.starwarsuniverseexplorer.utils.AppFactory
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.utils.extractIdFromUrl
import com.example.starwarsuniverseexplorer.viewmodel.VehiclesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclesListScreen(
    vm: VehiclesViewModel,
    onBack: () -> Unit,
    onVehicleClick: (Int) -> Unit
) {
    val vm = viewModel<VehiclesViewModel>(factory = AppFactory.vehiclesVMFactory)
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vehículos") },
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
                onRetry = vm::retry,
                modifier = Modifier.padding(padding)
            )
            is NetworkResult.Success -> {
                val vehicles = (state as NetworkResult.Success).data
                LazyColumn(
                    modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(vehicles) { v ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable {
                                    val id = extractIdFromUrl(v.url)
                                    if (id > 0) onVehicleClick(id)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text(v.name, style = MaterialTheme.typography.titleMedium)
                                Text("Modelo: ${v.model}")
                                Text("Fabricante: ${v.manufacturer}")
                            }
                        }
                    }
                }
            }
        }
    }
}