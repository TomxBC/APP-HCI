package com.example.turnsmart_hci.data.ui.devices

import com.example.turnsmart_hci.data.model.Blind
import com.example.turnsmart_hci.data.model.Error

data class BlindUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: Blind? = null
)

val BlindUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading