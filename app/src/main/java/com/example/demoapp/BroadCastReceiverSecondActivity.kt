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
import android.widget.TextView
import androidx.core.app.NotificationCompat

class BroadCastReceiverSecondActivity : AppCompatActivity() {
    private  lateinit var  tvSecondBatteryPer : TextView
    private  lateinit var notificationManager: NotificationManager
    private  lateinit var notificationChannel : NotificationChannel
    private  lateinit var  br  : BroadcastReceiver
    val intent = IntentFilter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_receiver_second)



        tvSecondBatteryPer = findViewById(R.id.tvSecondBetteryPercentage)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        br = object  : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                val percentage = p1!!.getIntExtra("level",0)
                tvSecondBatteryPer.text = percentage.toString()
                notificationBatteryPer()
            }
       }
        intent.addAction(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(br, intent)
    }

     fun onDestroyOne() : String {
        super.onDestroy()
        unregisterReceiver(br)
        return "Destroy"
    }
    override fun onResume() {
        super.onResume()
        registerReceiver(br,intent)
    }



    fun notificationBatteryPer(){
//        val  intentAction = Intent(this,BroadCastReceiverSecondActivity::class.java)
//        intentAction.actio
//        var pendingIntent = PendingIntent.getBroadcast(applicationContext,101,intentAction,PendingIntent.FLAG_IMMUTABLE)
        val  intentAction1 = Intent(this,BroadCastReceiverSecondActivity::class.java)
        intentAction1.action = onDestroyOne()
       val pendingIntent1 = PendingIntent.getBroadcast(applicationContext,102,intentAction1,PendingIntent.FLAG_IMMUTABLE)




        val builder = NotificationCompat.Builder(this,"Battery Level two")
            .setSmallIcon(R.drawable.ic_battery_charging_full)
            .setContentTitle("Battery")
            .setContentText(tvSecondBatteryPer.text.toString())
//            .addAction(R.drawable.snap_icon,"Start",pendingIntent)
            .setAutoCancel(false)
            .addAction(R.drawable.snap_icon,"Stop",pendingIntent1)
        notificationChannel = NotificationChannel("Battery Level two","Battery Percentage",NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(2,builder.build())
    }





}