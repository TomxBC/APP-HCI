package com.example.turnsmart_hci.data.remote.model

import com.example.turnsmart_hci.data.model.Speaker
import com.example.turnsmart_hci.data.model.Song

class RemoteSpeaker : RemoteDevice<RemoteSpeakerState>() {
    override fun asModel(): Speaker {
        val song = state.song?.let {
            Song(
                title = it.title,
                artist = it.artist,
                album = it.album,
                duration = it.duration,
                progress = it.progress
            )
        }

        return Speaker(
            id = id,
            name = name,
            status = RemoteStatus.asModel(state.status),
            volume = state.volume,
            song = song,
            genre = state.genre
        )
    }
}