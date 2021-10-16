package com.example.alarmkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.widget.Button

class AlarmActivity : AppCompatActivity() {
    @SuppressLint("InvalidWakeLockTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm2)

        val  alarmbuttonturnoff = findViewById<Button>(R.id.alarmbutton)

//        PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP
//        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
//        val pm =  getSystemService(Context.POWER_SERVICE) as PowerManager
//        val wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"wake")
//        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "wake");
//        wakeLock .acquire();

        var mp = MediaPlayer.create(applicationContext,R.raw.alarm_tune)
        mp.start()


       alarmbuttonturnoff.setOnClickListener{
           mp.stop()
           finish();
       }
    }
}