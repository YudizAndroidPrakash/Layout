package com.example.demoapp.pushnotificaitondemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.demoapp.R
import com.example.demoapp.jsondemo.ListOfProductsActivity
import com.example.demoapp.mapdemo.MapsActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage









class PushNotificationService : FirebaseMessagingService() {
    lateinit var builder : NotificationCompat.Builder
    private val channel_id = "push notification"
    lateinit var channel : NotificationChannel
    lateinit var notificationManager : NotificationManager
    lateinit var intent : Intent

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("token",token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//        Toast.makeText(this, "${message.data}", Toast.LENGTH_SHORT).show()
        if (message.data != null) {
            when (message.data.keys.toString()) {
                "1" -> {
                    intent = Intent(this, PushNotificationDemoMainActivity::class.java)
                }
                "2" -> {
                    intent = Intent(this, MapsActivity::class.java)
                }
                "3" -> {
                    intent = Intent(this, ListOfProductsActivity::class.java)
                }
                else -> {
                    intent = Intent(this, PushNotificationDemoMainActivity::class.java)
                }
            }
            sendNotification(message.notification!!.title.toString(),
                message.data[message.data.keys.toString()].toString())


        }


    }

    private fun sendNotification(notificationTitle : String,notificationContent : String) {
        val pendingIntent = PendingIntent.getActivity(this,2,intent,PendingIntent.FLAG_IMMUTABLE)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder = NotificationCompat.Builder(this,channel_id).apply {
                setSmallIcon(R.drawable.snap_icon)
                setContentTitle(notificationTitle)
                setContentText(notificationContent)
                setAutoCancel(true)
                setContentIntent(pendingIntent)
            }
            channel = NotificationChannel(channel_id,packageName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

        }else {
            builder = NotificationCompat.Builder(this).apply {
                setSmallIcon(R.drawable.snap_icon)
                setContentTitle("From Firebase")
                setAutoCancel(true)
                setContentIntent(pendingIntent)
            }
            channel = NotificationChannel(channel_id,packageName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

        }

        notificationManager.notify(1,builder.build())
    }
}