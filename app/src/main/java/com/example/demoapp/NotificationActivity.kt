package com.example.demoapp


import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat


class NotificationActivity : AppCompatActivity() {

    private lateinit var btnNormal: Button
    private lateinit var btnFullScreen: Button
    private lateinit var btnFullCustom: Button
    private lateinit var channel: NotificationChannel
    private var channelIDNormalNot = "normalNotification"
    private var channelIDFullSNot= "fullSCreeNotification"
    private var channelIDFullSCNot = "fullCustomNotification"

    private lateinit var builder: NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btnNormal = findViewById(R.id.btnNormalNotification)
        btnFullScreen = findViewById(R.id.btnFullScreenNotification)
        btnFullCustom = findViewById(R.id.btnFullYCustom)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnNormal.setOnClickListener {
            normalNotification()
        }

        btnFullScreen.setOnClickListener {
            fullScreenNotification()
        }

        btnFullCustom.setOnClickListener {
            fullCustmoNotification()
        }


    }

//    private fun createChannelGroup() {
//        val groupID = "my_first_group"
//        val groupName = getString(R.string.app_name)
//        notificationManager.createNotificationChannelGroup(NotificationChannelGroup(groupID,groupName))
//    }

    private fun fullCustmoNotification() {
        val fullyCustomNotification = Intent(this, MainActivity::class.java)
        val fullyCustomNotificationPending =
            PendingIntent.getActivity(this, 3, fullyCustomNotification, PendingIntent.FLAG_IMMUTABLE)
        val notificationLayout = RemoteViews(packageName, R.layout.activity_collapse)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.activity_expanded)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = NotificationCompat.Builder(this, channelIDFullSCNot)
                .setSmallIcon(R.drawable.ic_call)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
                .setContentIntent(fullyCustomNotificationPending)
                .setPriority(NotificationCompat.DEFAULT_LIGHTS)
                .setAutoCancel(true)
            channel = NotificationChannel(
                channelIDFullSCNot,
                "Custom Notification",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Custom Notification"
            }

            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(3, builder.build())

    }

    private fun fullScreenNotification() {
        val fullScreen = Intent(this, MainActivity::class.java)
        val fullScreenPendingIntent =
            PendingIntent.getActivity(this, 2, fullScreen, PendingIntent.FLAG_MUTABLE)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = NotificationCompat.Builder(this, channelIDFullSNot)
                .setSmallIcon(R.drawable.ic_call)
                .setContentTitle("Miss Call")
                .setContentText("+91 1234567890")
                .setCategory(NotificationCompat.CATEGORY_CALL)
                .setContentIntent(fullScreenPendingIntent)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .setAutoCancel(true)
            channel = NotificationChannel(
                channelIDFullSNot,
                "Reminder",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Good Morning"
            }
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(2, builder.build())
    }



    private fun normalNotification() {
        val bitmapImage = BitmapFactory.decodeResource(resources, R.drawable.imgavatar)

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_MUTABLE)
        val bigStyleText =
            "hello hellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohello"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = NotificationCompat.Builder(this, channelIDNormalNot)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Alert")
                .setContentText("Hello how are You ?")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(bigStyleText)
                )
                .setLargeIcon(bitmapImage)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmapImage)
//                        .bigLargeIcon(null)
                )

                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            channel = NotificationChannel(
                channelIDNormalNot, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Hello Every One"
            }
            notificationManager.createNotificationChannel(channel)
        } else {
            builder = NotificationCompat.Builder(this, channelIDNormalNot)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Alert")
                .setContentText("Hello how are You ?")
                .setLargeIcon(bitmapImage)
                .setStyle(
                    NotificationCompat.BigPictureStyle().bigPicture(bitmapImage)
                )
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
        notificationManager.notify(1, builder.build())
    }


}


