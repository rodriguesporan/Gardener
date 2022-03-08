package com.rodriguesporan.gardener.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodriguesporan.gardener.data.FloraUiState
import com.rodriguesporan.gardener.data.Message
import com.rodriguesporan.gardener.utilities.growPlants
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

    fun onViewCreated() {
        launchFetchPlantsJobAndNotifyLoadingInProgress()
    }

    fun userMessageShown(messageId: Long) {
        _uiState.update { currentUiState ->
            val messages = currentUiState.userMessages.filterNot { it.id == messageId }
            currentUiState.copy(userMessages = messages, isLoading = false)
        }
    }

    private fun launchFetchPlantsJobAndNotifyLoadingInProgress() {
        var fetchJob: Job? = null

        fetchJob = viewModelScope.launch {
            fetchJob?.cancel()
            notifyLoadingInProgress()
            delay(1000L)
            try {
                fetchPlantsAndUpdateState()
            } catch (throwable: Throwable) {
                handleErrorAndUpdateState(throwable)
            }
        }
    }

    private fun notifyLoadingInProgress() {
        _uiState.update { currentUIState ->
            currentUIState.copy(isLoading = true)
        }
    }

    private fun fetchPlantsAndUpdateState() {
        val plants = growPlants()

        _uiState.update { currentUIState ->
            currentUIState.copy(plantList = plants, isLoading = false)
        }
    }

    private fun handleErrorAndUpdateState(throwable: Throwable) {
        throwable.message?.let { messageValue ->
            _uiState.update { currentUIState ->
                val messages = currentUIState.userMessages + Message(
                    id = UUID.randomUUID().mostSignificantBits,
                    message = messageValue
                )
                currentUIState.copy(userMessages = messages, isLoading = false)
            }
        }
    }
}
