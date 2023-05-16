package com.example.demoapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat

class BroadCastReceiverOne : AppCompatActivity() {
    private  lateinit var tvBatteryPer : TextView
   private  lateinit var notificationManager: NotificationManager
   private  lateinit var notificationChannel : NotificationChannel

   private  lateinit var  btnSecond : Button




    private  var  br  : BroadcastReceiver    = object  : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            tvBatteryPer = findViewById(R.id.tvForBetteryPercentage)
            val percentage = p1!!.getIntExtra("level",0)
            tvBatteryPer.text = percentage.toString()
            notificationBatteryPer()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_receiver_one)
        registerReceiver(br,IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        btnSecond = findViewById(R.id.btnSecondBroadcast)
        btnSecond.setOnClickListener {
            val i = Intent(this,BroadCastReceiverSecondActivity::class.java)
            startActivity(i)
        }

    }
//        Toast.makeText(applicationContext, "${tvBatteryPer.text}", Toast.LENGTH_SHORT).show()
    fun notificationBatteryPer(){
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val intent = Intent(this,BroadCastReceiverOne::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this,101,intent,PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(this,"Battery Level")
            .setSmallIcon(R.drawable.ic_battery_charging_full)
            .setContentTitle("Battery")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentText(tvBatteryPer.text.toString())
        notificationChannel = NotificationChannel("Battery Level","Battery Percentage",NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1,builder.build())
    }


}