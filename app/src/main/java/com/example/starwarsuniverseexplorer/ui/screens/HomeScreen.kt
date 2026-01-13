package com.example.starwarsuniverseexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.starwarsuniverseexplorer.R
import androidx.compose.ui.unit.dp
import com.example.starwarsuniverseexplorer.ui.components.StarWarsButton

@Composable
fun HomeScreen(
    onPeople: () -> Unit,
    onFilms: () -> Unit,
    onPlanets: () -> Unit,
    onStarships: () -> Unit,
    onVehicles: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "STAR WARS UNIVERSE\nEXPLORER",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Image(
            painter = painterResource(id = R.drawable.starwars_logo),
            contentDescription = "Star Wars Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(8.dp))

        StarWarsButton("PERSONAJES", onPeople)
        StarWarsButton("PELÍCULAS", onFilms)
        StarWarsButton("PLANETAS", onPlanets)
        StarWarsButton("NAVES", onStarships)
        StarWarsButton("VEHÍCULOS", onVehicles)
    }
}