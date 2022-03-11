package com.rodriguesporan.gardener.domain

import com.rodriguesporan.gardener.network.PlantRepository
import com.rodriguesporan.gardener.data.PlantsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchPlantsUseCase(
    private val plantRepository: PlantRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(): PlantsResponse =
        withContext(defaultDispatcher) {
            plantRepository.fetchPlants()
        }
}
