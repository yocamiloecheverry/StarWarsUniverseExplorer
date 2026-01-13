package com.example.starwarsuniverseexplorer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val StarWarsDarkScheme = darkColorScheme(
    primary = SwYellow,
    onPrimary = Color.Black,

    secondary = SwBlue,
    onSecondary = Color.Black,

    background = SpaceBlack,
    onBackground = Color.White,

    surface = SpaceSurface,
    onSurface = Color.White,

    surfaceVariant = SpaceSurface2,
    onSurfaceVariant = Color.White,

    outline = Color(0xFF2A3555)
)

@Composable
fun StarWarsUniverseExplorerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = StarWarsDarkScheme,
        typography = Typography(),
        content = content
    )
}
