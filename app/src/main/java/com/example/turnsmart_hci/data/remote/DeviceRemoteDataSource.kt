package com.example.turnsmart_hci.data.remote

import android.util.Log
import com.example.turnsmart_hci.data.remote.api.DeviceService
import com.example.turnsmart_hci.data.remote.model.RemoteDevice
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeviceRemoteDataSource(
    private val deviceService: DeviceService
) : RemoteDataSource() {

    val devices: Flow<List<RemoteDevice<*>>> = flow {
        while (true) {
            val devices = handleApiResponse {
                deviceService.getDevices()
            }
            emit(devices)
            delay(DELAY)
        }
    }

    suspend fun getDeviceType(deviceTypeId: String): List<RemoteDevice<*>> {
        return handleApiResponse {
            deviceService.getDeviceType(deviceTypeId)
        }
    }
    suspend fun getDevice(deviceId: String): RemoteDevice<*> {
        return handleApiResponse {
            deviceService.getDevice(deviceId)
        }
    }

    suspend fun addDevice(device: RemoteDevice<*>): RemoteDevice<*> {
        return handleApiResponse {
            deviceService.addDevice(device)
        }
    }

    suspend fun modifyDevice(device: RemoteDevice<*>): Boolean {
        return handleApiResponse {
            deviceService.modifyDevice(device.id, device)
        }
    }

    suspend fun deleteDevice(deviceId: String): Boolean {
        return handleApiResponse {
            deviceService.deleteDevice(deviceId)
        }
    }

    suspend fun executeDeviceAction(
        deviceId: String,
        action: String,
        parameters: Array<*>
    ): Array<*> {
        return handleApiResponse {
            deviceService.executeDeviceAction(deviceId, action, parameters)
        }
    }

    companion object {
        const val DELAY: Long = 10000
    }
}