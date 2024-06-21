package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LampViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LampUiState())
    val uiState: StateFlow<LampUiState> = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.devices.map { devices ->
                devices.filterIsInstance<Lamp>()
            }
        ) { state, response ->
            state.copy(lamps = response)
        }
    }

    fun turnOn(lamp: Lamp) = runOnViewModelScope(
        { repository.executeDeviceAction(lamp.id, Lamp.TURN_ON_ACTION) },
        { state, _ -> state }
    )

    fun turnOff(lamp: Lamp) = runOnViewModelScope(
        { repository.executeDeviceAction(lamp.id, Lamp.TURN_OFF_ACTION) },
        { state, _ -> state }
    )

    fun setColor(lamp: Lamp, color: String) = runOnViewModelScope(
        { repository.executeDeviceAction(lamp.id, Lamp.SET_COLOR, arrayOf(color)) },
        { state, _ -> state}
    )

    fun setBrightness(lamp: Lamp, brightness: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(lamp.id, Lamp.SET_BRIGHTNESS, arrayOf(brightness)) },
        { state, _ -> state}
    )

    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (LampUiState, T) -> LampUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (LampUiState, R) -> LampUiState
    ): Job = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiState.update { updateState(it, response).copy(loading = false) }
        }.onFailure { e ->
            _uiState.update { it.copy(loading = false, error = handleError(e)) }
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}

