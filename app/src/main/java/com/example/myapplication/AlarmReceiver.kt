package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class AlarmReceiver: BroadcastReceiver() {
    companion object{
        const val  ID = "CHANEL_NAME"
        const val chanelname = "CHANEL_NAME"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val manager:NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val chanelNotification:NotificationCompat.Builder = NotificationCompat.Builder(context, ID)
            .setContentTitle(R.id.title.toString())
            .setContentText(R.id.summary.toString())
            .setSmallIcon(R.drawable.ic_launcher_background)
        val channel = NotificationChannel(ID, chanelname, NotificationManager.IMPORTANCE_HIGH)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            manager.createNotificationChannel(channel)
            manager.notify(1, chanelNotification.build())
        }

    }
}