package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.model.Status
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LampViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LampUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.currentDevice
        ) { state, response -> state.copy(currentDevice = response as Lamp?) }
    }

    fun getCurrentName(): String? {
        return uiState.value.currentDevice?.name
    }

    fun getCurrentStatus(): Status? {
        return uiState.value.currentDevice?.status
    }

    fun getCurrentColor(): String? {
        return uiState.value.currentDevice?.color
    }

    fun getCurrentBrightness(): Int? {
        return uiState.value.currentDevice?.brightness
    }
    fun turnOn() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.TURN_ON_ACTION) },
        { state, _ -> state }
    )

    fun turnOff() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.TURN_OFF_ACTION) },
        { state, _ -> state }
    )

    fun setColor(color: String) = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.SET_COLOR, arrayOf(color)) },
        { state, _ -> state}
    )

    fun setBrightness(bright: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.SET_BRIGHTNESS, arrayOf(bright)) },
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

    private fun handleError(e: Throwable): Error? {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}