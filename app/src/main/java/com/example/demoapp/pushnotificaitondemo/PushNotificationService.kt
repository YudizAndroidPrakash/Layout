package com.example.demoapp.pushnotificaitondemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.demoapp.MainActivity
import com.example.demoapp.R
import com.example.demoapp.jsondemo.ListOfProductsActivity
import com.example.demoapp.mapdemo.MapsActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class PushNotificationService : FirebaseMessagingService() {
    lateinit var builder: NotificationCompat.Builder
    private val channelId = "push notification"
    private lateinit var channel: NotificationChannel
    private lateinit var notificationManager: NotificationManager
    lateinit var intent: Intent

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG_TOKEN", token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//        Toast.makeText(this, "${message.data}", Toast.LENGTH_SHORT).show()
        Log.d("Token_message", "$message")
        sendNotification(
            message.notification!!.title!!,
            message.data["2"].toString(),
            checkValueFromPayLoad(message)
        )
    }

    private fun checkValueFromPayLoad(keyOfData: RemoteMessage): PendingIntent {
        intent = when (keyOfData.data["2"]) {
            "Recharge" -> {
                Intent(this, RechargeActivity::class.java)
            }
            "SpecialOffers" -> {
                Intent(this, SpecialOfferActivity::class.java)
            }
            "BigDeal" -> {
                Intent(this, BigDealActivity::class.java)
            }
            else -> {
                Intent(this, MainActivity::class.java)
            }
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 2, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(this,2,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        }


    }

    private fun sendNotification(notificationTitle : String,notificationContent : String,pendingIntent: PendingIntent) {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder = NotificationCompat.Builder(this,channelId).apply {
                setSmallIcon(R.drawable.snap_icon)
                setContentTitle(notificationTitle)
                setContentText(notificationContent)
                setAutoCancel(true)
                setContentIntent(pendingIntent)
            }
            channel = NotificationChannel(channelId,packageName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

        }else {
            builder = NotificationCompat.Builder(this).apply {
                setSmallIcon(R.drawable.snap_icon)
                setContentTitle("From Firebase")
                setAutoCancel(true)
                setContentIntent(pendingIntent)
            }
        }
        notificationManager.notify(1,builder.build())
    }
}