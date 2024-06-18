package com.example.turnsmart_hci.data.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.turnsmart_hci.ApiApplication
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import com.example.turnsmart_hci.data.ui.devices.DevicesViewModel
import com.example.turnsmart_hci.data.ui.devices.LampViewModel

//import com.example.turnsmart_hci.ApiApplication
//import ar.edu.itba.example.api.repository.DeviceRepository
//import ar.edu.itba.example.api.repository.RoomRepository
//import ar.edu.itba.example.api.ui.devices.DevicesViewModel
//import ar.edu.itba.example.api.ui.devices.LampViewModel
//import ar.edu.itba.example.api.ui.rooms.RoomsViewModel

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as ApiApplication)
    val deviceRepository = application.deviceRepository
    return ViewModelFactory(
        deviceRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}

class ViewModelFactory (
    private val deviceRepository: DeviceRepository,
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

            isAssignableFrom(LampViewModel::class.java) ->
                LampViewModel(deviceRepository)

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}