package com.example.turnsmart_hci.data.model;

import com.example.turnsmart_hci.data.remote.model.RemoteDevice
import com.example.turnsmart_hci.data.remote.model.RemoteSpeakerState
import com.example.turnsmart_hci.data.remote.model.RemoteSpeaker

class Speaker (
    id: String?,
    name: String,
    val room: Room?,
    val status: Status,
    val song: String?,
    val volume: Int
    ) : Device(id, name, DeviceType.SPEAKER){

    override fun asRemoteModel(): RemoteDevice<RemoteSpeakerState> {
        val state = RemoteSpeakerState()
        state.status = Status.asRemoteModel(status)
        state.volume = volume
        state.song = song

        val model = RemoteSpeaker()
        model.id = id
        model.name = name
        model.room = room?.asRemoteModel()
        model.setState(state)
        return model
    }

    companion object {
        const val PLAY_ACTION = "play"
        const val STOP_ACTION = "stop"
        const val PAUSE_ACTION = "pause"
    }
}
