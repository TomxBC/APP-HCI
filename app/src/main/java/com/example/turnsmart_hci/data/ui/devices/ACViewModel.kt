package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.AC
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ACViewModel (
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ACUiState())
    //val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.devices.map { devices ->
                val acDevices = devices.filterIsInstance<AC>()
                println("AC Devices: $acDevices")
                acDevices
            }
        ) { state, response ->
            state.copy(ac = response)
        }
    }


    fun turnOn(ac: AC) = runOnViewModelScope(
        { repository.executeDeviceAction(ac.id, AC.TURN_ON_ACTION) },
        { state, _ -> state }
    )

    fun turnOff(ac: AC) = runOnViewModelScope(
        { repository.executeDeviceAction(ac.id, AC.TURN_OFF_ACTION) },
        { state, _ -> state }
    )

    fun setTemperature(ac: AC, temp: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(ac.id, AC.SET_TEMPERATURE_ACTION, arrayOf(temp)) },
        { state, _ -> state }
    )

    fun setMode(ac: AC, mode: String) = runOnViewModelScope(
        { repository.executeDeviceAction(ac.id, AC.SET_MODE_ACTION, arrayOf(mode)) },
        { state, _ -> state }
    )

    fun setVerticalSwing(ac: AC, position: String) = runOnViewModelScope(
        { repository.executeDeviceAction(ac.id, AC.SET_VERTICAL_SWING_ACTION, arrayOf(position)) },
        { state, _ -> state }
    )

    fun setHorizontalSwing(ac: AC, position: String) = runOnViewModelScope(
        { repository.executeDeviceAction(ac.id, AC.SET_HORIZONTAL_SWING_ACTION, arrayOf(position)) },
        { state, _ -> state }
    )

    fun setFanSpeed(ac: AC, speed: String) = runOnViewModelScope(
        { repository.executeDeviceAction(ac.id, AC.SET_FAN_SPEED_ACTION, arrayOf(speed)) },
        { state, _ -> state }
    )


    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (ACUiState, T) -> ACUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (ACUiState, R) -> ACUiState
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