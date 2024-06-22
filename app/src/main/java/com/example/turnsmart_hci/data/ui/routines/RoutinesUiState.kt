package com.example.turnsmart_hci.data.ui.routines

import com.example.turnsmart_hci.data.model.Routine
import com.example.turnsmart_hci.data.model.Error

data class RoutinesUiState(
    val isFetching: Boolean = false,
    val error: Error? = null,
    val routines: List<Routine> = emptyList()
)