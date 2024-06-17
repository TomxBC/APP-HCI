package com.example.turnsmart_hci.data.remote.model

import com.google.gson.annotations.SerializedName

class RemoteBlindState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("level")
    var level: Int = 0
}