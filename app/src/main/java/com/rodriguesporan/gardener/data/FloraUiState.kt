package com.rodriguesporan.gardener.data

data class FloraUiState(
    val plants: List<Plant> = listOf(),
    val userMessages: List<Message> = listOf(),
    val isLoading: Boolean = false
)
