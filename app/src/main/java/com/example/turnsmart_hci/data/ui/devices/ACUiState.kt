package com.example.turnsmart_hci.data.ui.devices

import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.AC


data class ACUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: AC? = null
)

val ACUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading