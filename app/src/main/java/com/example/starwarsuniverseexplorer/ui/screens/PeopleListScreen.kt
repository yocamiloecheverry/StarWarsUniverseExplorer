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
import com.example.starwarsuniverseexplorer.ui.components.ErrorView
import com.example.starwarsuniverseexplorer.ui.components.LoadingView
import com.example.starwarsuniverseexplorer.utils.NetworkResult
import com.example.starwarsuniverseexplorer.utils.extractIdFromUrl
import com.example.starwarsuniverseexplorer.viewmodel.PeopleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListScreen(
    vm: PeopleViewModel,
    onBack: () -> Unit,
    onPersonClick: (String) -> Unit
) {
    val state by vm.peopleState.collectAsState()
    val query by vm.query.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Personajes") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("←") }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // Buscador
            OutlinedTextField(
                value = query,
                onValueChange = vm::onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Buscar personaje (ej: Luke)") }
            )

            Spacer(Modifier.height(12.dp))

            // Estados
            when (state) {
                is NetworkResult.Loading -> {
                    LoadingView()
                }

                is NetworkResult.Error -> {
                    val msg = (state as NetworkResult.Error).message
                    ErrorView(message = msg, onRetry = vm::retry)
                }

                is NetworkResult.Success -> {
                    val people = (state as NetworkResult.Success).data

                    if (people.isEmpty()) {
                        Text(
                            text = "No se encontraron resultados.",
                            style = MaterialTheme.typography.titleMedium
                        )
                        return@Column
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(people) { person ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable { onPersonClick(person.url) },
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
                            ) {
                                Column(Modifier.padding(12.dp)) {
                                    Text(
                                        text = person.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(text = "Género: ${person.gender}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}