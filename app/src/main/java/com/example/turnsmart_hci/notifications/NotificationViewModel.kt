package com.example.turnsmart_hci.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import com.example.turnsmart_hci.R

class NotificationViewModel : ViewModel() {
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

