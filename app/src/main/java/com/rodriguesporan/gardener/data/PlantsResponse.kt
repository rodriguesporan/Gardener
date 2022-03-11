package com.rodriguesporan.gardener.data

sealed class PlantsResponse {

    data class Success(val plants: List<PlantDTO> = emptyList()) : PlantsResponse()
    data class Error(
        val exception: Exception, val plants: List<PlantDTO> = emptyList()
    ) : PlantsResponse()
}
