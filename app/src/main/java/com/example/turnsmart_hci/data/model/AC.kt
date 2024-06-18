package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteAC
import com.example.turnsmart_hci.data.remote.model.RemoteACState
import com.example.turnsmart_hci.data.remote.model.RemoteDevice

class AC (
    id: String?,
    name: String,
    val status: Status,
    val mode: String,
    val temperature: Int,
    val horizontalSwing: Int,
    val verticalSwing: Int,
    val fanFast: Int
    ) : Device(id, name, DeviceType.AC) {

    override fun asRemoteModel(): RemoteDevice<RemoteACState> {
        val state = RemoteACState()
        state.status = Status.asRemoteModel(status)
        state.temperature = temperature
        state.verticalSwing = verticalSwing
        state.mode = mode
        state.horizontalSwing = horizontalSwing
        state.fanFast = fanFast

        val model = RemoteAC()
        model.id = id
        model.name = name
        model.setState(state)
        return model
    }

    companion object {
        const val TURN_ON_ACTION = "turnOn"
        const val TURN_OFF_ACTION = "turnOff"
    }
}
