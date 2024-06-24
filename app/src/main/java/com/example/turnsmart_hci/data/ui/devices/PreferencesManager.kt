package com.example.turnsmart_hci.data.ui.devices

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun setFavorite(deviceId: String, isFavorite: Boolean) {
        preferences.edit().putBoolean(deviceId, isFavorite).apply()
    }

    fun isFavorite(deviceId: String): Boolean {
        return preferences.getBoolean(deviceId, false)
    }
}
