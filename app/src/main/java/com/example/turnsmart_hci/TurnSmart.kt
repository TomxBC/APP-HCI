package com.example.turnsmart_hci

import android.app.Application
import com.example.turnsmart_hci.data.remote.DeviceRemoteDataSource
import com.example.turnsmart_hci.data.remote.RoutineRemoteDataSource
import com.example.turnsmart_hci.data.remote.api.RetrofitClient
import com.example.turnsmart_hci.data.repositry.DeviceRepository
import com.example.turnsmart_hci.data.repositry.RoutineRepository

class TurnSmart  : Application() {

    private val deviceRemoteDataSource: DeviceRemoteDataSource
        get() = DeviceRemoteDataSource(RetrofitClient.deviceService)

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.routineService)

    val deviceRepository: DeviceRepository
        get() = DeviceRepository(deviceRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)
}