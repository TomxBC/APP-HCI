package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.ConcreteDeviceType
import com.google.gson.annotations.SerializedName

class RemoteDeviceType (
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("powerUsage")
    var powerUsage: Int? = null
) {
    fun asModel(): ConcreteDeviceType {
        return ConcreteDeviceType(id, name, powerUsage)
    }

    companion object {
        const val LAMP_DEVICE_TYPE_ID = "go46xmbqeomjrsjr"
        const val SPEAKER_DEVICE_TYPE_ID = "c89b94e8581855bc"
        const val BLINDS_DEVICE_TYPE_ID = "eu0v2xgprrhhg41g"
        const val AC_DEVICE_TYPE_ID = "li6cbv5sdlatti0j"
    }
}