package com.example.turnsmart_hci.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import com.example.turnsmart_hci.MainActivity
import com.example.turnsmart_hci.R

class NotificationViewModel : ViewModel() {
    fun sendNotification(context: Context) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, NotificationChannel.CHANNEL_ID)
            .setSmallIcon(R.mipmap.turn_smart_logo_round)
            .setContentTitle("Test Notification")
            .setContentText("This is a test notification")
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification) // Use a constant ID for simplicity
    }
}

