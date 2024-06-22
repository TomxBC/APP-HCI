package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteDeviceType

class ConcreteDeviceType (
    var id: String,
    var name: String,
    var powerUsage: Int? = null
) {
    fun asRemoteModel(): RemoteDeviceType {
        return RemoteDeviceType(id, name, powerUsage)
    }
}