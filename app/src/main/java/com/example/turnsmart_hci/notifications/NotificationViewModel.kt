package com.example.turnsmart_hci.notifications

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.turnsmart_hci.R

class NotificationViewModel : ViewModel() {
    private val _selectedLanguage = MutableLiveData<String>()
    val selectedLanguage: LiveData<String> = _selectedLanguage

    private val _notificationsEnabled = MutableLiveData<Boolean>()
    val notificationsEnabled: LiveData<Boolean> = _notificationsEnabled

    fun setLanguage(language: String) {
        _selectedLanguage.value = language
        // Implement logic to update app language
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        _notificationsEnabled.value = enabled
        // Implement logic to handle notifications

    }

    fun sendNotification(context: Context, message: String, title: String) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, NotificationChannelApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.turnsmart)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(title.hashCode(), notification)
    }
}

