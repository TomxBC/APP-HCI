package com.example.turnsmart_hci.data.remote.api

import com.example.turnsmart_hci.data.remote.model.RemoteDevice
import com.example.turnsmart_hci.data.remote.model.RemoteDeviceType
import com.example.turnsmart_hci.data.remote.model.RemoteLamp
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DeviceTypeAdapter : JsonDeserializer<RemoteDevice<*>?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): RemoteDevice<*>? {
        val gson = Gson()
        val jsonDeviceObject = json.asJsonObject
        val jsonDeviceTypeObject = jsonDeviceObject["type"].asJsonObject
        val deviceTypeId = jsonDeviceTypeObject["id"].asString
        return if (deviceTypeId == RemoteDeviceType.LAMP_DEVICE_TYPE_ID) {
            gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteLamp?>() {}.type)
        } else if(deviceTypeId == RemoteDeviceType.AC_DEVICE_TYPE_ID) {
            gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteLamp?>() {}.type)
        } else if(deviceTypeId == RemoteDeviceType.SPEAKER_DEVICE_TYPE_ID) {
            gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteLamp?>() {}.type)
        } else if(deviceTypeId == RemoteDeviceType.BLINDS_DEVICE_TYPE_ID) {
            gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteLamp?>() {}.type)
        } else null
    }
}

//Cambiar los "RemoteLamps por los dispositivos"