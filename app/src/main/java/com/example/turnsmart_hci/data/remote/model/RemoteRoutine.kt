package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.Routine
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RemoteRoutine (
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("actions")
    @Expose(serialize = false)
    var actions: List<RemoteAction> = emptyList()
) {
    fun asModel() : Routine {
        return Routine(
            id = id,
            name = name,
            actions = actions.map { it.asModel() }
        )
    }
}

