package com.rodriguesporan.gardener.utilities

import com.rodriguesporan.gardener.data.PlantUiState

fun growPlants(): List<PlantUiState> {
    return listOf(
        PlantUiState("malus-pumila", "Apple"),
        PlantUiState("beta-vulgaris", "Beet"),
        PlantUiState("coriandrum-sativum", "Cilantro"),
        PlantUiState("solanum-lycopersicum", "Tomato"),
        PlantUiState("persea-americana", "Avocado"),
        PlantUiState("pyrus-communis", "Pear"),
        PlantUiState("solanum-melongena", "Eggplant"),
        PlantUiState("vitis-vinifera", "Grape"),
        PlantUiState("mangifera-indica", "Mango"),
        PlantUiState("citrus-x-sinensis", "Orange"),
        PlantUiState("helianthus-annuus", "Sunflower"),
        PlantUiState("citrullus-lanatus", "Watermelon"),
        PlantUiState("hibiscus-rosa-sinensis", "Hibiscus"),
        PlantUiState("cypripedium-reginae", "Pink & White Lady's Slipper"),
        PlantUiState("aquilegia-coerulea", "Rocky Mountain Columbine"),
        PlantUiState("magnolia-denudata", "Yulan Magnolia"),
        PlantUiState("bougainvillea-glabra", "Bougainvillea")
    )
}