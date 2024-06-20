package com.example.turnsmart_hci.data.repositry

import com.example.turnsmart_hci.data.model.Device
import com.example.turnsmart_hci.data.model.Lamp
import com.example.turnsmart_hci.data.remote.DeviceRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeviceRepository(
    private val remoteDataSource: DeviceRemoteDataSource
) {
    val devices: Flow<List<Device>> =
        remoteDataSource.devices
            .map { it.map { jt -> jt.asModel() } }

    val currentDevice = devices.map { it.firstOrNull { jt -> jt is Lamp } }

    suspend fun getDevice(deviceId: String): Device {
        return remoteDataSource.getDevice(deviceId).asModel()
    }
    suspend fun getDeviceType(deviceTypeId: String): List<Device> {
        return remoteDataSource.getDeviceType(deviceTypeId)
            .map { remoteDevice -> remoteDevice.asModel() }
    }

    suspend fun addDevice(device: Device): Device {
        return remoteDataSource.addDevice(device.asRemoteModel()).asModel()
    }

    suspend fun modifyDevice(device: Device): Boolean {
        return remoteDataSource.modifyDevice(device.asRemoteModel())
    }

    suspend fun deleteDevice(deviceId: String): Boolean {
        return remoteDataSource.deleteDevice(deviceId)
    }

    suspend fun executeDeviceAction(
        deviceId: String,
        action: String,
        parameters: Array<*> = emptyArray<Any>()
    ): Array<*> {
        return remoteDataSource.executeDeviceAction(deviceId, action, parameters)
    }
}