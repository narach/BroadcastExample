package com.example.broadcastexample

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastexample.databinding.ActivityMainBinding
import com.example.broadcastexample.receivers.MessageReceiver

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var br: BroadcastReceiver? = null

    private val WHERE_MY_CAT_ACTION = "com.example.broadcastexample.CAT"
    private val ALARM_MESSAGE = "Срочно пришлите кота!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSendMessage.setOnClickListener {
                sendMessage()
            }
        }

        br = MessageReceiver()
        val filter = IntentFilter(WHERE_MY_CAT_ACTION)
        registerReceiver(br, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }

    private fun sendMessage() {
        val intent = Intent()
        intent.action = WHERE_MY_CAT_ACTION
        intent.putExtra("com.example.broadcastexample.Message", ALARM_MESSAGE)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }
}