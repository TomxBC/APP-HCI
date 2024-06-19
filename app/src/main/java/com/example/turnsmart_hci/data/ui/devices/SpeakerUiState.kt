package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Speaker

data class SpeakerUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: Speaker? = null
)

val SpeakerUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading