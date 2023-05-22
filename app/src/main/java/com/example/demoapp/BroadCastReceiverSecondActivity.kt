package com.example.demoapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.registerReceiver

class BroadCastReceiverSecondActivity : AppCompatActivity() {
    private lateinit var channel: NotificationChannel
    private  lateinit var  tvSecondBatteryPer : TextView
    private  lateinit var notificationManager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder
    private var channelIDFullSCNot = "fullCustomNotification"

    private  lateinit var  br  : BroadcastReceiver
    val intent = IntentFilter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_receiver_second)

        tvSecondBatteryPer = findViewById(R.id.tvSecondBetteryPercentage)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager




        br = object  :  BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {


                when(p1!!.action){
                    "start" -> {
                        Toast.makeText(p0, "Start", Toast.LENGTH_SHORT).show()
                    }
                    "stop"-> {
                        Toast.makeText(p0, "Stop", Toast.LENGTH_SHORT).show()
                    }
                }







                val percentage = p1!!.getIntExtra("level",0)
                tvSecondBatteryPer.text = percentage.toString()
                notificationBatteryPer()
            }
       }
        intent.addAction(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(br, intent)
        notificationBatteryPer()
    }


    override fun onResume() {
        super.onResume()
        registerReceiver(br,intent)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }





    fun notificationBatteryPer() {
//        val notificationLayoutExpanded = RemoteViews(packageName,R.layout.activity_bettary_percantage_notification)
//        val fullyCustomNotification = Intent(this, MainActivity::class.java).setAction("start")
//        val fullyCustomNotificationPending =
//            PendingIntent.getBroadcast(this, 3, fullyCustomNotification, PendingIntent.FLAG_MUTABLE)


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder = NotificationCompat.Builder(this, channelIDFullSCNot)
//                .setSmallIcon(R.drawable.ic_call)
//                .setContentTitle("Bettary")
//                .setContentText(tvSecondBatteryPer.text.toString())
//                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
//                .setCustomBigContentView(notificationLayoutExpanded)
//                .setContentIntent(fullyCustomNotificationPending)
//                .setPriority(NotificationCompat.DEFAULT_LIGHTS)
//                .setAutoCancel(true)
//            channel = NotificationChannel(
//                channelIDFullSCNot,
//                "Custom Notification",
//                NotificationManager.IMPORTANCE_HIGH
//            ).apply {
//                description = "Custom Notification"
//            }
//            notificationManager.createNotificationChannel(channel)
//        }else {
//            builder = NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_call)
//                .setContentTitle("Bettary")
//                .setContentText(tvSecondBatteryPer.text.toString())
//                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
//                .setCustomBigContentView(notificationLayoutExpanded)
//                .setContentIntent(fullyCustomNotificationPending)
//                .setPriority(NotificationCompat.DEFAULT_LIGHTS)
//                .setAutoCancel(true)
//        }
//        notificationManager.notify(3, builder.build())



        val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            PendingIntent.FLAG_MUTABLE
        }else {
            0
        }


        val bettarychange  = Intent(this,BroadCastReceiverSecondActivity::class.java)
        bettarychange.action ="start"
        sendBroadcast(bettarychange)

        val pendingIntentAction : PendingIntent = PendingIntent.getBroadcast(applicationContext,0,bettarychange,flag)

        val bettarychangeStop  = Intent(this,BroadCastReceiverSecondActivity::class.java)
        bettarychangeStop.action = "stop"
        sendBroadcast(bettarychangeStop)
        val pendingIntentAction1 : PendingIntent = PendingIntent.getBroadcast(applicationContext,0,bettarychangeStop,flag)
//        var pendingIntent = PendingIntent.getBroadcast(applicationContext,101,intentAction,PendingIntent.FLAG_IMMUTABLE)
//
//
//        val  intentAction1 = Intent(this,BroadCastReceiverSecondActivity::class.java).setAction("Start")
//       val pendingIntent1 = PendingIntent.getBroadcast(applicationContext,102,intentAction1,PendingIntent.FLAG_IMMUTABLE)


        val builder = NotificationCompat.Builder(this,"Battery Level two")
            .setSmallIcon(R.drawable.ic_battery_charging_full)
            .setContentTitle("Battery")
            .setContentText(tvSecondBatteryPer.text.toString())
            .addAction(0,"Start",pendingIntentAction)
            .addAction(0,"Stop",pendingIntentAction1)
            .setAutoCancel(false)
        channel = NotificationChannel("Battery Level two","Battery Percentage",NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(2,builder.build())
    }
}


