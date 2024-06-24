package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DevicesViewModel(
    private val repository: DeviceRepository,
    private val preferencesManager: PreferencesManager // Recibe PreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.devices
        ) { state, response ->
            val updatedResponse = response.map { device ->
                device.favorite = preferencesManager.isFavorite(device.id)
                device
            }
            state.copy(devices = updatedResponse)
        }
    }

    fun toggleFavorite(deviceId: String) {
        viewModelScope.launch {
            val updatedDevices = _uiState.value.devices.map { device ->
                if (device.id == deviceId) {
                    device.favorite = !device.favorite
                    preferencesManager.setFavorite(deviceId, device.favorite)
                }
                device
            }
            _uiState.update { it.copy(devices = updatedDevices) }
        }
    }
    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (DevicesUiState, T) -> DevicesUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}

