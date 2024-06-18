package com.example.turnsmart_hci.data.ui.devices

import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Lamp


data class LampUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: Lamp? = null
)

val LampUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading