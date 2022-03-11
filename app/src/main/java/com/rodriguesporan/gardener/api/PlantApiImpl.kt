package com.rodriguesporan.gardener.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rodriguesporan.gardener.data.PlantDTO
import com.rodriguesporan.gardener.data.PlantsResponse
import com.rodriguesporan.gardener.data.PlantsResponse.Success
import com.rodriguesporan.gardener.data.PlantsResponse.Error

const val PLANT_DATA_FILENAME = "plants.json"

class PlantApiImpl(val context: Context): PlantApi {

    override fun fetchPlants(): PlantsResponse {
        try {
            context.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                val listType = object : TypeToken<List<PlantDTO>>() {}.type
                val plants: List<PlantDTO> = Gson().fromJson(inputStream.reader(), listType)

                return Success(plants)
            }
        } catch (e: Exception) {
            return Error(e)
        }
    }
}
