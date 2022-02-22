package com.rodriguesporan.gardener.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class SplashViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.LoadingState)
    val uiState: StateFlow<UiState> get() = _uiState

    fun onCreateSplashActivity() {
        viewModelScope.launch {
            delay(2000L)
            _uiState.value = UiState.CreatedState
        }
    }

    sealed class UiState {
        object LoadingState: UiState()
        object CreatedState: UiState()
    }
}