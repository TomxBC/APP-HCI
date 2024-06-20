package com.example.turnsmart_hci.data.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.turnsmart_hci.TurnSmart
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import com.example.turnsmart_hci.data.repositry.RoutineRepository
import com.example.turnsmart_hci.data.ui.devices.ACViewModel
import com.example.turnsmart_hci.data.ui.devices.BlindViewModel
import com.example.turnsmart_hci.data.ui.devices.DevicesViewModel
import com.example.turnsmart_hci.data.ui.devices.LampViewModel
import com.example.turnsmart_hci.data.ui.devices.SpeakerViewModel
import com.example.turnsmart_hci.data.ui.routines.RoutineViewModel
import com.example.turnsmart_hci.data.ui.routines.RoutinesViewModel


@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as TurnSmart)
    val deviceRepository = application.deviceRepository
    val routineRepository = application.routineRepository
    return ViewModelFactory(
        deviceRepository,
        routineRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}

class ViewModelFactory (
    private val deviceRepository: DeviceRepository,
    private val routinesRepository: RoutineRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(DevicesViewModel::class.java) ->
                DevicesViewModel(deviceRepository)

            isAssignableFrom(ACViewModel::class.java) ->
                ACViewModel(deviceRepository)

            isAssignableFrom(BlindViewModel::class.java) ->
                BlindViewModel(deviceRepository)

            isAssignableFrom(SpeakerViewModel::class.java) ->
                SpeakerViewModel(deviceRepository)

            isAssignableFrom(LampViewModel::class.java) ->
                LampViewModel(deviceRepository)

            isAssignableFrom(RoutinesViewModel::class.java) ->
                RoutinesViewModel(routinesRepository)

            isAssignableFrom(RoutineViewModel::class.java) ->
                RoutineViewModel(routinesRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}