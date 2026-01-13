package com.example.starwarsuniverseexplorer.utils

private val idRegex = Regex(""".*/(\d+)/?$""")

fun extractIdFromUrl(url: String): Int {
    val u = url.trim()
    val match = idRegex.find(u) ?: return -1
    return match.groupValues[1].toIntOrNull() ?: -1
}