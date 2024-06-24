package com.example.turnsmart_hci.data.model

import com.example.turnsmart_hci.data.remote.model.RemoteDevice
import com.example.turnsmart_hci.data.remote.model.RemoteSpeakerState
import com.example.turnsmart_hci.data.remote.model.RemoteSpeaker
import com.example.turnsmart_hci.data.remote.model.RemoteSong

data class Song(
    val title: String?,
    val artist: String?,
    val album: String?,
    val duration: String?,
    val progress: String?
) {
    fun asRemoteModel(): RemoteSong {
        return RemoteSong().apply {
            title = this@Song.title
            artist = this@Song.artist
            album = this@Song.album
            duration = this@Song.duration
            progress = this@Song.progress
        }
    }
}

class Speaker (
    id: String,
    name: String,
    val status: Status,
    val song: Song?,
    val volume: Int,
    val genre: String?,
    favorite: Boolean = false
) : Device(id, name, DeviceType.SPEAKER, favorite) {

    override fun asRemoteModel(): RemoteDevice<RemoteSpeakerState> {
        val state = RemoteSpeakerState().apply {
            status = Status.asRemoteModel(this@Speaker.status)
            volume = this@Speaker.volume
            song = this@Speaker.song?.asRemoteModel()
            genre = this@Speaker.genre
        }

        return RemoteSpeaker().apply {
            id = this@Speaker.id
            name = this@Speaker.name
            setState(state)
        }
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
