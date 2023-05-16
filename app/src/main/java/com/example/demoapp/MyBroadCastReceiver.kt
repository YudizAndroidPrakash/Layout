package com.example.demoapp


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService


class MyBroadCastReceiver(private var tvBatteryPer : TextView) : BroadcastReceiver() {
    //    private  lateinit var notificationManager: NotificationManager
//    private  lateinit var notificationChannel : NotificationChannel
//    override fun onReceive(p0: Context?, p1: Intent?) {
//        val percentage = p1!!.getIntExtra("level",0)
//        tvBatteryPer.text = percentage.toString()
//    }
// fun notificationBatteryPer(){
//        notificationManager = getSystemService(context.NOTIFICATION_SERVICE) as NotificationManager
//        val intent = Intent(this,BroadCastReceiverOne::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this,101,intent, PendingIntent.FLAG_IMMUTABLE)
//        val builder = NotificationCompat.Builder(this,"Battery Level")
//            .setSmallIcon(R.drawable.ic_battery_charging_full)
//            .setContentTitle("Battery")
//            .addAction(R.drawable.snap_icon,"Level",pendingIntent)
//            .addAction(R.drawable.snap_icon,"level2",pendingIntent)
//            .setContentText(tvBatteryPer.text)
//        notificationChannel = NotificationChannel("Battery Level","Battery Percentage",
//            NotificationManager.IMPORTANCE_HIGH)
//        notificationManager.createNotificationChannel(notificationChannel)
//        notificationManager.notify(1,builder.build())
//    }
    override fun onReceive(p0: Context?, p1: Intent?) {
    }
}