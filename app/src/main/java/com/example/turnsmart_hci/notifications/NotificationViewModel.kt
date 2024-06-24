package com.example.turnsmart_hci.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import com.example.turnsmart_hci.R

class NotificationViewModel : ViewModel() {
    fun sendActivatedNotifications(context: Context, @StringRes msgDevices: Int,@StringRes msgRoutines: Int, @StringRes title: Int){
        sendNotification(context, msgDevices, context.getString(title))
    }
    fun sendNotification(context: Context, @StringRes messageRes: Int, title: String, vararg formatArgs: Any) {
        val message = context.getString(messageRes, *formatArgs)
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, NotificationChannelApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.turnsmart)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(title.hashCode(), notification)
    }

//    fun sendNotification(context: Context, @StringRes messageRes: Int, title: String) {
//        val message = context.getString(messageRes)
//        val notificationManager = context.getSystemService(NotificationManager::class.java)
//        val notification = NotificationCompat.Builder(context, NotificationChannelApp.CHANNEL_ID)
//            .setSmallIcon(R.drawable.turnsmart)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setAutoCancel(true)
//            .build()
//
//        notificationManager.notify(title.hashCode(), notification)
//    }

}


