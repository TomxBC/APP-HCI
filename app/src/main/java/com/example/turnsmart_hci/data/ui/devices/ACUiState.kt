package com.example.turnsmart_hci.data.ui.devices

import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.AC



data class ACUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val ac: List<AC> = emptyList()
)
