package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteBlind
import com.example.turnsmart_hci.data.remote.model.RemoteBlindState
import com.example.turnsmart_hci.data.remote.model.RemoteDevice


class Blind (
    id: String,
    name: String,
    val status: Status,
    val level: Int,
    favorite: Boolean = false
) : Device(id, name, DeviceType.BLINDS) {
    override fun asRemoteModel(): RemoteDevice<RemoteBlindState> {
        val state = RemoteBlindState()
        state.status = Status.asRemoteModel(status)
        state.level = level

        val model = RemoteBlind()
        model.id = id
        model.name = name
        model.setState(state)
        return model
    }

    companion object {
        const val OPEN_ACTION = "open"
        const val CLOSE_ACTION = "close"
        const val SET_LEVEL_ACTION = "setLevel"
    }

}