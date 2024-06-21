package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.model.Speaker
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpeakerViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpeakerUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.devices.map { devices ->
                devices.filterIsInstance<Speaker>()
            }
        ) { state, response ->
            state.copy(speakers = response)
        }
    }

    fun setVolume(speaker: Speaker, volume: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.SET_VOLUME_ACTION, arrayOf(volume)) },
        { state, _ -> state }
    )

    fun play(speaker: Speaker) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.PLAY_ACTION) },
        { state, _ -> state }
    )

    fun stop(speaker: Speaker) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.STOP_ACTION) },
        { state, _ -> state }
    )

    fun pause(speaker: Speaker) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.PAUSE_ACTION) },
        { state, _ -> state }
    )

    fun resume(speaker: Speaker) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.RESUME_ACTION) },
        { state, _ -> state }
    )

    fun nextSong(speaker: Speaker) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.NEXT_SONG_ACTION) },
        { state, _ -> state }
    )

    fun previousSong(speaker: Speaker) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.PREVIOUS_SONG_ACTION) },
        { state, _ -> state }
    )

    fun setGenre(speaker: Speaker,genre: String) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.SET_GENRE_ACTION, arrayOf(genre)) },
        { state, _ -> state }
    )

    fun getPlaylist(speaker: Speaker) = runOnViewModelScope(
        { repository.executeDeviceAction(speaker.id, Speaker.GET_PLAYLIST_ACTION) },
        { state, _ -> state }
    )

    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (SpeakerUiState, T) -> SpeakerUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (SpeakerUiState, R) -> SpeakerUiState
    ): Job = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiState.update { updateState(it, response).copy(loading = false) }
        }.onFailure { e ->
            _uiState.update { it.copy(loading = false, error = handleError(e)) }
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}
