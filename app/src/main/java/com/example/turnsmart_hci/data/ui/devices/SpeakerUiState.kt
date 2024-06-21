package com.example.turnsmart_hci.data.ui.devices


import com.example.turnsmart_hci.data.model.Error

import com.example.turnsmart_hci.data.model.Speaker

data class SpeakerUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val speakers: List<Speaker> = emptyList()
)

