package com.example.starwarsuniverseexplorer.utils

import java.io.IOException

fun mapError(e: Exception): String {
    return when (e) {
        is IOException -> "Sin conexión. Verifica tu internet e inténtalo de nuevo."
        else -> e.message ?: "Ocurrió un error inesperado."
    }
}