package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Blind
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlindViewModel(
    private val repository: DeviceRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(BlindUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.devices.map { devices ->
                devices.filterIsInstance<Blind>()
            }
        ) { state, response ->
            state.copy(blinds = response)
        }
    }

    fun toggleFavorite(deviceId: String) {
        val currentFavoriteState = preferencesManager.isFavorite(deviceId)
        preferencesManager.setFavorite(deviceId, !currentFavoriteState)
    }

    fun open(blind : Blind) = runOnViewModelScope(
        { repository.executeDeviceAction(blind.id, Blind.OPEN_ACTION) },
        { state, _ -> state }
    )

    fun close(blind : Blind) = runOnViewModelScope(
        { repository.executeDeviceAction(blind.id, Blind.CLOSE_ACTION) },
        { state, _ -> state }
    )

    fun setLevel(blind : Blind, level: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(blind.id, Blind.SET_LEVEL_ACTION, arrayOf(level))},
        { state, _ -> state}
    )


    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (BlindUiState, T) -> BlindUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (BlindUiState, R) -> BlindUiState
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
