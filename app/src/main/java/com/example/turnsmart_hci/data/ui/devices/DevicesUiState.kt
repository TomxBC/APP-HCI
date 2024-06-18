package com.example.turnsmart_hci.data.ui.devices

import com.example.turnsmart_hci.data.model.Device
import com.example.turnsmart_hci.data.model.Error


data class DevicesUiState(
    val isFetching: Boolean = false,
    val error: Error? = null,
    val devices: List<Device> = emptyList()
)