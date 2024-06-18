package com.example.turnsmart_hci.data.remote.model

import com.google.gson.annotations.SerializedName

class RemoteACState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("mode")
    lateinit var mode: String

    @SerializedName("temperature")
    var temperature: Int = 0

    @SerializedName("verticalSwing")
    var verticalSwing: Int = 0

    @SerializedName("horizontalSwing")
    var horizontalSwing: Int = 0

    @SerializedName("fanFast")
    var fanFast: Int = 0

}