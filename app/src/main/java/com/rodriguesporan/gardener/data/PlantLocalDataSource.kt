package com.rodriguesporan.gardener.data

import com.rodriguesporan.gardener.api.PlantApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PlantLocalDataSource(
    private val plantApi: PlantApi,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchPlants(): PlantsResponse =
        withContext(ioDispatcher) {
            plantApi.fetchPlants()
        }
}
