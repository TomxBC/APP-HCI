package com.example.turnsmart_hci.data.ui.routines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Action
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Routine
import com.example.turnsmart_hci.data.repositry.RoutineRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutineViewModel(
    private val repository: RoutineRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoutineUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.currentRoutine
        ) { state, response -> state.copy(currentRoutine = response) }
    }

    fun getCurrentName(): String? {
        return uiState.value.currentRoutine?.name
    }

    fun getCurrentActions(): List<Action>? {
        return uiState.value.currentRoutine?.actions
    }

    fun executeRoutine(routine: Routine) = runOnViewModelScope(
        { repository.executeRoutine(routine.id) },
        { state, _ -> state }
    )

    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (RoutineUiState, T) -> RoutineUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (RoutineUiState, R) -> RoutineUiState
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