package com.example.turnsmart_hci.data.remote.model;

import com.example.turnsmart_hci.data.model.Speaker

class RemoteSpeaker : RemoteDevice<RemoteSpeakerState>(){
    override fun asModel(): Speaker {
        return Speaker(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            volume = state.volume,
            song = state.song
        )
    }
}
