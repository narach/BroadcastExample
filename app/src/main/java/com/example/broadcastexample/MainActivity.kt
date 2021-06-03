package com.example.broadcastexample

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastexample.databinding.ActivityMainBinding
import com.example.broadcastexample.receivers.MessageReceiver
import com.example.broadcastexample.receivers.TimeBroadcastReceiver


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var br: BroadcastReceiver? = null
    private val timeBroadcastReceiver = TimeBroadcastReceiver()

    private val WHERE_MY_CAT_ACTION = "com.example.broadcastexample.CAT"
    private val ACTION_POWER_DISCONNECTION = "android.intent.action.ACTION_POWER_DISCONNECTED"
    private val ALARM_MESSAGE = "Срочно пришлите кота!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSendMessage.setOnClickListener {
                sendMessage()
            }

            btnRegister.setOnClickListener {
                registerBroadcastReceiver()
            }

            btnUnregister.setOnClickListener {
                unregisterBroadcastReceiver()
            }
        }

        br = MessageReceiver()
//        val filter = IntentFilter(WHERE_MY_CAT_ACTION)
        val filter = IntentFilter()
        filter.addAction(WHERE_MY_CAT_ACTION)
        filter.addAction(ACTION_POWER_DISCONNECTION)
        registerReceiver(br, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun sendMessage() {
        val intent = Intent()
        intent.action = WHERE_MY_CAT_ACTION
        intent.putExtra("com.example.broadcastexample.Message", ALARM_MESSAGE)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }

    private fun registerBroadcastReceiver() {
        registerReceiver(timeBroadcastReceiver, IntentFilter(
            "android.intent.action.TIME_TICK"
        ))
        Toast.makeText(
            applicationContext, "Приёмник включен",
            Toast.LENGTH_SHORT).show()
    }

    private fun unregisterBroadcastReceiver() {
        unregisterReceiver(timeBroadcastReceiver)
        Toast.makeText(applicationContext, "Приёмник выключён", Toast.LENGTH_SHORT)
            .show()
    }
}