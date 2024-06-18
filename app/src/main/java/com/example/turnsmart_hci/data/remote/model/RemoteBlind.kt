package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.Blind
import com.example.turnsmart_hci.data.model.Lamp

class RemoteBlind : RemoteDevice<RemoteBlindState>() {

    override fun asModel(): Blind {
        return Blind(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            level = state.level
        )
    }
}