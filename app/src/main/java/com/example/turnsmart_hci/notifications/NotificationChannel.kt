package com.example.turnsmart_hci.notifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class NotificationChannel : Application(){
    companion object{
        const val CHANNEL_ID = "channel_id"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Device Status",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Updates on Device status"
        val notificationManager = this.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

}