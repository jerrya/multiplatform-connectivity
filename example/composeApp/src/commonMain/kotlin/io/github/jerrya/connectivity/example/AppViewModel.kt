package io.github.jerrya.connectivity.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrya.connectivity.ConnectivityRepository
import io.github.jerrya.connectivity.getConnectivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState = _uiState.asStateFlow()

    private val connectivityRepository = getConnectivity()

    init {
        viewModelScope.launch {
            connectivityRepository.latestStatus().collect { status ->
                val isOnline = when (status) {
                    is ConnectivityRepository.Status.Online -> true
                    is ConnectivityRepository.Status.Offline -> false
                    else -> false
                }
                _uiState.update {
                    it.copy(
                        isOnline = isOnline,
                    )
                }
            }
        }
    }


}

data class UiState(
    val isOnline: Boolean = true
)