package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.Action
import com.example.turnsmart_hci.data.model.Routine
import com.google.gson.annotations.SerializedName

class RemoteRoutine (
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("actions")
    var actions: List<RemoteAction> = arrayListOf()
) {
    fun asModel() : Routine {
        return Routine(
            id = id,
            name = name,
            actions = actions.map { it.asModel() }
        )
    }
}

