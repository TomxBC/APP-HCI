package com.example.turnsmart_hci.data.model;

import com.example.turnsmart_hci.data.remote.model.RemoteDevice
import com.example.turnsmart_hci.data.remote.model.RemoteSpeakerState
import com.example.turnsmart_hci.data.remote.model.RemoteSpeaker

class Speaker (
    id: String?,
    name: String,
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
        model.setState(state)
        return model
    }

    companion object {
        const val SET_VOLUME_ACTION = "setVolume"
        const val PLAY_ACTION = "play"
        const val STOP_ACTION = "stop"
        const val PAUSE_ACTION = "pause"
        const val RESUME_ACTION = "resume"
        const val NEXT_SONG_ACTION = "nextSong"
        const val PREVIOUS_SONG_ACTION = "previousSong"
        const val SET_GENRE_ACTION = "setGenre"
        const val GET_PLAYLIST_ACTION = "getPlaylist"
    }
}
