package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.AC
import android.util.Log

class RemoteAC: RemoteDevice<RemoteACState>() {
    override fun asModel(): AC {
        return AC(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            mode = state.mode,
            temperature = state.temperature,
            verticalSwing = state.verticalSwing,
            horizontalSwing = state.horizontalSwing,
            fanSpeed = state.fanSpeed
            )
    }
}