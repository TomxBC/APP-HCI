package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteAC
import com.example.turnsmart_hci.data.remote.model.RemoteACState
import com.example.turnsmart_hci.data.remote.model.RemoteDevice

class AC (
    id: String,
    name: String,
    val status: Status,
    val mode: String,
    val temperature: Int,
    val horizontalSwing: String,
    val verticalSwing: String,
    val fanSpeed: String,
    favorite: Boolean = false
) : Device(id, name, DeviceType.AC) {

    override fun asRemoteModel(): RemoteDevice<RemoteACState> {
        val state = RemoteACState()
        state.status = Status.asRemoteModel(status)
        state.temperature = temperature
        state.verticalSwing = verticalSwing
        state.mode = mode
        state.horizontalSwing = horizontalSwing
        state.fanSpeed = fanSpeed

        val model = RemoteAC()
        model.id = id
        model.name = name
        model.setState(state)
        return model
    }

    companion object {
        const val TURN_ON_ACTION = "turnOn"
        const val TURN_OFF_ACTION = "turnOff"
        const val SET_TEMPERATURE_ACTION = "setTemperature"
        const val SET_MODE_ACTION = "setMode"
        const val SET_VERTICAL_SWING_ACTION = "setVerticalSwing"
        const val SET_HORIZONTAL_SWING_ACTION ="setHorizontalSwing"
        const val SET_FAN_SPEED_ACTION = "setFanSpeed"
    }
}
