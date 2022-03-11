package com.rodriguesporan.gardener.network

import com.rodriguesporan.gardener.data.PlantLocalDataSource
import com.rodriguesporan.gardener.data.PlantsResponse

class PlantRepository(private val plantLocalDataSource: PlantLocalDataSource) {

    suspend fun fetchPlants(): PlantsResponse = plantLocalDataSource.fetchPlants()
}
