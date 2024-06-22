package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteAction

class Action(
    val device: Device,
    val actionName: String,
    val params: Array<*>,
    val meta: Any?
) {

    fun asRemoteModel(): RemoteAction {
        return RemoteAction(device.asRemoteModel(), actionName, params, meta)
    }
}