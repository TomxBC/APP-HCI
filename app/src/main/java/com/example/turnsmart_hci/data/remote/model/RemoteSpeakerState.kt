package com.example.turnsmart_hci.data.remote.model;

import com.google.gson.annotations.SerializedName;

class RemoteSpeakerState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("volume")
    var volume: Int = 0

    @SerializedName("song")
    var song: String? = null

    @SerializedName("genre")
    var genre: String? = null
}
