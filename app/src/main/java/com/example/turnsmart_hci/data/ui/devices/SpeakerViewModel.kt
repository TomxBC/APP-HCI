package com.example.turnsmart_hci.data.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turnsmart_hci.DataSourceException
import com.example.turnsmart_hci.data.model.Error
import com.example.turnsmart_hci.data.model.Speaker
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpeakerViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpeakerUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.currentDevice
        ) { state, response -> state.copy(currentDevice = response as Speaker?) }
    }

    fun setVolume(volume: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.SET_VOLUME_ACTION, arrayOf(volume)) },
        { state, _ -> state }
    )

    fun play() = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.PLAY_ACTION) },
        { state, _ -> state }
    )

    fun stop() = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.STOP_ACTION) },
        { state, _ -> state }
    )

    fun pause() = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.PAUSE_ACTION) },
        { state, _ -> state }
    )

    fun resume() = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.RESUME_ACTION) },
        { state, _ -> state }
    )

    fun nextSong() = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.NEXT_SONG_ACTION) },
        { state, _ -> state }
    )

    fun previousSong() = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.PREVIOUS_SONG_ACTION) },
        { state, _ -> state}
    )

    fun setGenre(genre: String) = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.SET_GENRE_ACTION, arrayOf(genre)) },
        { state, _ -> state }
    )

    fun getPlaylist() = runOnViewModelScope(
        { repository.executeDeviceAction(_uiState.value.currentDevice?.id!!, Speaker.GET_PLAYLIST_ACTION) },
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

    private fun handleError(e: Throwable): Error? {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }

}