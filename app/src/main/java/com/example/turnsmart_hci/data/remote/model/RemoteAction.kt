package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.Action
import com.google.gson.annotations.SerializedName

class RemoteAction (
    @SerializedName("device")
    var device: RemoteDevice<*>? = null,

    @SerializedName("actionName")
    var actionName: String? = null,

    @SerializedName("params")
    var params: Array<*> = emptyArray<Any>(),

    @SerializedName("meta")
    var meta: Any? = null
) {
    fun asModel() : Action {
        return Action(
            device = device?.asModel(),
            actionName = actionName,
            params = params,
            meta = meta
        )
    }
}