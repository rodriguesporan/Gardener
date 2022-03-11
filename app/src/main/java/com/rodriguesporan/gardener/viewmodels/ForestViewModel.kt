package com.rodriguesporan.gardener.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodriguesporan.gardener.api.PlantApiImpl
import com.rodriguesporan.gardener.data.FloraUiState
import com.rodriguesporan.gardener.data.Plant
import com.rodriguesporan.gardener.data.PlantLocalDataSource
import com.rodriguesporan.gardener.data.Message
import com.rodriguesporan.gardener.data.PlantsResponse.Success
import com.rodriguesporan.gardener.data.PlantsResponse.Error
import com.rodriguesporan.gardener.domain.FetchPlantsUseCase
import com.rodriguesporan.gardener.network.PlantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class ForestViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FloraUiState(emptyList()))
    val uiState: StateFlow<FloraUiState> get() = _uiState.asStateFlow()

    fun onViewCreated(context: Context) {
        launchFetchPlantsJobAndNotifyLoadingInProgress(context)
    }

    private fun launchFetchPlantsJobAndNotifyLoadingInProgress(context: Context) {
        var fetchJob: Job? = null

        fetchJob = viewModelScope.launch {
            fetchJob?.cancel()
            notifyLoadingInProgress()
            delay(1000L)
            try {
                fetchPlantsAndUpdateState(context)
            } catch (exception: Exception) {
                handleErrorAndUpdateState(exception)
            }
        }
    }

    private fun fetchPlantsAndUpdateState(context: Context) {
        val plantApiImpl = PlantApiImpl(context)
        val plantLocalDataSource = PlantLocalDataSource(plantApiImpl, Dispatchers.IO)
        val plantRepository = PlantRepository(plantLocalDataSource)
        val fetchPlantsUseCase = FetchPlantsUseCase(plantRepository)

        viewModelScope.launch {
            when (val response = fetchPlantsUseCase()) {
                is Success -> {
                    val plantsList = response.plants.map { it.toPlant() }
                    handleSuccessAndUpdateState(plantsList)
                }
                is Error -> handleErrorAndUpdateState(response.exception)
            }
        }
    }

    fun userMessageShown(messageId: Long) {
        _uiState.update { currentUiState ->
            val messages = currentUiState.userMessages.filterNot { it.id == messageId }
            currentUiState.copy(userMessages = messages, isLoading = false)
        }
    }

    private fun notifyLoadingInProgress() {
        _uiState.update { currentUIState ->
            currentUIState.copy(isLoading = true)
        }
    }

    private fun handleSuccessAndUpdateState(plantsList: List<Plant>) {
        _uiState.update { currentUIState ->
            currentUIState.copy(
                plants = plantsList,
                isLoading = false
            )
        }
    }

    private fun handleErrorAndUpdateState(exception: Exception) {
        _uiState.update { currentUIState ->
            val messages = currentUIState.userMessages + Message(
                id = UUID.randomUUID().mostSignificantBits,
                message = exception.message ?: "Generic exception"
            )

            currentUIState.copy(
                plants = emptyList(),
                userMessages = messages,
                isLoading = false
            )
        }
    }
}
