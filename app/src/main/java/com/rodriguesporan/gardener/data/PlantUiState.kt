package com.rodriguesporan.gardener.data

data class PlantUiState(
    val plantId: String,
    val name: String,
    val isPlanted: Boolean = false
)
