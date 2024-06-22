package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteAction
import com.example.turnsmart_hci.data.remote.model.RemoteRoutine

class Routine(
    val id: String,
    val name: String,
    val actions: List<Action>
) {
    fun asRemoteModel(): RemoteRoutine {
        val remoteActionList = arrayListOf<RemoteAction>()

        for (action in actions) {
            remoteActionList.add(action.asRemoteModel())
        }

        return RemoteRoutine(id, name, remoteActionList)
    }
}