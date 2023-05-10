package com.example.demoapp

import android.annotation.SuppressLint
import android.app.LauncherActivity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri.Builder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import java.nio.channels.Channel


class NotificationActivity : AppCompatActivity() {

    private lateinit var btnNormalNotCol: Button
    private lateinit var btnNormalNotExap: Button
    private lateinit var notificationManager: NotificationManager

        private lateinit var builder : NotificationCompat.Builder
    private var channelId = "com.example.demoapp"
    private var description1 = "First Notification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btnNormalNotCol = findViewById(R.id.btnNormalNotColl)
        btnNormalNotExap = findViewById(R.id.btnNormalNotExpand)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnNormalNotCol.setOnClickListener {
            val intent = Intent(this, Notification::class.java).apply {
                action
            }
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

            val largeIcon = BitmapFactory.decodeResource(resources,R.drawable.img)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                builder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_home_24)
                    .setContentTitle("Alert")
                    .setContentText("Alert from the Demo app")
                    .setLargeIcon(largeIcon)
                    .setStyle(NotificationCompat.BigPictureStyle()
                        .bigPicture(largeIcon)
                        .bigLargeIcon(null)
                       )
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                val channel = NotificationChannel(
                    channelId, getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_MIN
                ).apply {
                    description = description1
                }
                notificationManager.createNotificationChannel(channel)
            }
//             else {
//                 builder = NotificationCompat.Builder(this, channelId)
//                    .setSmallIcon(R.drawable.ic_home_24)
//                    .setContentTitle("Alert")
//                    .setContentText("Alert from the Demo app")
//                    .setContentIntent(pendingIntent)
//
//
//            }
            notificationManager.notify(123, builder.build())

        }


    }


}