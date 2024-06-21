package com.example.turnsmart_hci.data.remote.api

import com.example.turnsmart_hci.data.remote.model.RemoteRoutine
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.jvm.Throws

class RoutineTypeAdapter : JsonDeserializer<RemoteRoutine?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): RemoteRoutine? {
        val gson = Gson()
        val jsonRoutineObject = json.asJsonObject
        return gson.fromJson(jsonRoutineObject, object : TypeToken<RemoteRoutine>() {}.type)
    }
}