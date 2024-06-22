package com.example.turnsmart_hci.data.remote.model

import com.google.gson.annotations.SerializedName

class RemoteSong {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("artist")
    var artist: String? = null

    @SerializedName("album")
    var album: String? = null

    @SerializedName("duration")
    var duration: String? = null

    @SerializedName("progress")
    var progress: String? = null
}
