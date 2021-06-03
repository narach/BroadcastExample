package com.example.broadcastexample.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class TimeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val formatter = SimpleDateFormat("hh:mm:ss a")
        val msgStr = "Текущее время: ${formatter.format(Date())}"
        Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show()
        Log.d("TimeBroadcastReceiver", msgStr)
    }
}