package com.rodriguesporan.gardener.api

import com.rodriguesporan.gardener.data.PlantsResponse

interface PlantApi {

    fun fetchPlants(): PlantsResponse
}
