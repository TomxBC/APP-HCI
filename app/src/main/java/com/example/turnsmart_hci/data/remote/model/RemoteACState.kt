package com.example.turnsmart_hci.data.remote.model

import com.google.gson.annotations.SerializedName

class RemoteACState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("mode")
    lateinit var mode: String

    @SerializedName("temperature")
    var temperature: Int = 24

    @SerializedName("verticalSwing")
    lateinit var verticalSwing: String

    @SerializedName("horizontalSwing")
    lateinit var horizontalSwing: String

    @SerializedName("fanSpeed")
    lateinit var fanSpeed: String

}