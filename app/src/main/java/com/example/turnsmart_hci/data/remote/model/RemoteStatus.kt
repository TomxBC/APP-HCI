package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.Status

object RemoteStatus {
    const val ON = "on"
    const val OFF = "off"
    const val OPENED = "opened"
    const val CLOSED = "closed"
    const val PLAYING = "playing"
    const val PAUSED = "paused"
    const val STOPED = "stoped"

    fun asModel(status: String): Status {
        return when (status) {
            ON -> Status.ON
            OFF -> Status.OFF
            CLOSED -> Status.CLOSED
            OPENED -> Status.OPENED
            PLAYING -> Status.PLAYING
            PAUSED -> Status.PAUSED
            else -> Status.STOPED
        }
    }
}