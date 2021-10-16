package com.example.alarmkotlin
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


class MainActivity: FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/battery"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            // Note: this method is invoked on the main thread.
            // TODO



             if (call.method == "getAlarm"){




                val alarminmills = call.argument<String>("paramsfromflutter")

//              Print
//                 Log.d("Bhai2", alarminmills.toString())
//                 Log.d("Bhaibhai", (System.currentTimeMillis()).toString())


                 var  i = Intent(applicationContext, MyBroadcastReciever::class.java)
                 var pi = PendingIntent.getBroadcast(applicationContext, 111, i, 0)
                 var am : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                 am.setRepeating(AlarmManager.RTC_WAKEUP, alarminmills!!.toLong(), AlarmManager.INTERVAL_DAY, pi)
                 result.success("Alarm is created $alarminmills seconds")


             }

            else {
                result.notImplemented()
            }
        }
    }



}