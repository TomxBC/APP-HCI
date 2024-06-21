package com.example.turnsmart_hci.data.ui.devices

import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Lamp


data class LampUiState(
    val lamps: List<Lamp> = emptyList(),
    val loading: Boolean = false,
    val error: Error? = null,
)

