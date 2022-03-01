package com.rodriguesporan.gardener.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguesporan.gardener.data.Plant

class ForestViewModel : ViewModel() {

    private val _forestPlantList = MutableLiveData<List<Plant>>()
    val forestPlantList: LiveData<List<Plant>> get() = _forestPlantList

    fun onViewCreated() {
        _forestPlantList.value = fetchPlants()
    }

    private fun fetchPlants(): List<Plant> {
        return listOf(
            Plant("malus-pumila", "Apple"),
            Plant("beta-vulgaris", "Beet"),
            Plant("coriandrum-sativum", "Cilantro"),
            Plant("solanum-lycopersicum", "Tomato"),
            Plant("persea-americana", "Avocado"),
            Plant("pyrus-communis", "Pear"),
            Plant("solanum-melongena", "Eggplant"),
            Plant("vitis-vinifera", "Grape"),
            Plant("mangifera-indica", "Mango"),
            Plant("citrus-x-sinensis", "Orange"),
            Plant("helianthus-annuus", "Sunflower"),
            Plant("citrullus-lanatus", "Watermelon"),
            Plant("hibiscus-rosa-sinensis", "Hibiscus"),
            Plant("cypripedium-reginae", "Pink & White Lady's Slipper"),
            Plant("aquilegia-coerulea", "Rocky Mountain Columbine"),
            Plant("magnolia-denudata", "Yulan Magnolia"),
            Plant("bougainvillea-glabra", "Bougainvillea")
        )
    }
}