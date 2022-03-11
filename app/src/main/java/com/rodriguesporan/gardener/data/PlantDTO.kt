package com.rodriguesporan.gardener.data

data class PlantDTO(
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int,
    val imageUrl: String
) {

    fun toPlant(): Plant {
        return Plant(plantId, name)
    }
}
