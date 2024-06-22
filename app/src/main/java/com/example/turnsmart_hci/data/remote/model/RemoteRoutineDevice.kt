package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.RoutineDevice
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RemoteRoutineDevice (
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("type")
    @Expose(serialize = false)
    var type: RemoteDeviceType
) {
    fun asModel(): RoutineDevice {
        return RoutineDevice(id, name, type.asModel())
    }
}