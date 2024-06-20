package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteAction
import com.example.turnsmart_hci.data.remote.model.RemoteRoutine

class Routine(
    val id: String?,
    val name: String?,
    val actions: List<Action>
) {
    fun asRemoteModel(): RemoteRoutine {
        val remoteActionList = arrayListOf<RemoteAction>()

        for (action in actions) {
            remoteActionList.add(action.asRemoteModel())
        }

        val remoteRoutine = RemoteRoutine()
        remoteRoutine.id = id
        remoteRoutine.name = name
        remoteRoutine.actions = remoteActionList

        return remoteRoutine
    }
}