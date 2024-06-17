package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteBlind
import com.example.turnsmart_hci.data.remote.model.RemoteBlindState
import com.example.turnsmart_hci.data.remote.model.RemoteDevice
import com.example.turnsmart_hci.data.remote.model.RemoteLamp
import com.example.turnsmart_hci.data.remote.model.RemoteLampState

class Blind (
    id: String?,
    name: String,
    val room: Room?,
    val status: Status,
    val level: Int
    ) : Device(id, name, DeviceType.BLINDS) {
    override fun asRemoteModel(): RemoteDevice<RemoteBlindState> {
        val state = RemoteBlindState()
        state.status = Status.asRemoteModel(status)
        state.level = level

        val model = RemoteBlind()
        model.id = id
        model.name = name
        model.room = room?.asRemoteModel()
        model.setState(state)
        return model
    }

    companion object {
        const val OPEN_ACTION = "open"
        const val CLOSE_ACTION = "close"
    }

}