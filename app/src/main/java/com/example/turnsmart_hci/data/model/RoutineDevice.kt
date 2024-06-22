package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteDeviceType
import com.example.turnsmart_hci.data.remote.model.RemoteRoutine
import com.example.turnsmart_hci.data.remote.model.RemoteRoutineDevice

class RoutineDevice (
    var id: String,
    var name: String,
    var type: ConcreteDeviceType
) {
    fun asRemoteModel(): RemoteRoutineDevice {
        return RemoteRoutineDevice(id, name, type.asRemoteModel())
    }
}