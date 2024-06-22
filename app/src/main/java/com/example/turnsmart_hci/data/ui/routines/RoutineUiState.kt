package com.example.turnsmart_hci.data.ui.routines

import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Routine

data class RoutineUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentRoutine: Routine? = null
)

val RoutineUiState.canExecuteAction: Boolean get() = currentRoutine != null && !loading