package com.rodriguesporan.gardener.data

data class FloraUiState(
    val plantList: List<PlantUiState> = listOf(),
    val userMessages: List<Message> = listOf(),
    val isLoading: Boolean = false
)
