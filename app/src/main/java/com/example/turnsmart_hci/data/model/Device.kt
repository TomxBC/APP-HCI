package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteDevice

abstract class Device(
    val id: String,
    val name: String,
    val type: DeviceType,
    var favorite: Boolean = false
) {
    abstract fun asRemoteModel(): RemoteDevice<*>
}