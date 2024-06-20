package com.example.turnsmart_hci

import android.app.Application
import com.example.turnsmart_hci.data.remote.DeviceRemoteDataSource
import com.example.turnsmart_hci.data.remote.api.RetrofitClient
import com.example.turnsmart_hci.data.repositry.DeviceRepository

//import ar.edu.itba.example.api.remote.DeviceRemoteDataSource
//import ar.edu.itba.example.api.remote.RoomRemoteDataSource
//import ar.edu.itba.example.api.remote.api.RetrofitClient
//import ar.edu.itba.example.api.repository.DeviceRepository
//import ar.edu.itba.example.api.repository.RoomRepository

class TurnSmart  : Application() {

    private val deviceRemoteDataSource: DeviceRemoteDataSource
        get() = DeviceRemoteDataSource(RetrofitClient.deviceService)

    val deviceRepository: DeviceRepository
        get() = DeviceRepository(deviceRemoteDataSource)
}