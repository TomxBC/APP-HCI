package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteStatus

enum class Status {
    ON, OFF, CLOSED, OPENED, PLAYING, PAUSED, STOPED;

    companion object {
        fun asRemoteModel(value: Status): String {
            return when (value) {
                ON -> RemoteStatus.ON
                OFF -> RemoteStatus.OFF
                CLOSED -> RemoteStatus.CLOSED
                OPENED -> RemoteStatus.OPENED
                PLAYING -> RemoteStatus.PLAYING
                PAUSED -> RemoteStatus.PAUSED
                STOPED -> RemoteStatus.STOPED
            }
        }
    }
}