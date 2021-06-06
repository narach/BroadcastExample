package com.example.broadcastexample

import android.Manifest
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.broadcastexample.databinding.ActivityMainBinding
import com.example.broadcastexample.receivers.IncomingCallReceiver
import com.example.broadcastexample.receivers.TimeBroadcastReceiver


class MainActivity : AppCompatActivity() {

    private val permRequestCode = 2

    private lateinit var binding: ActivityMainBinding
    private var br: BroadcastReceiver? = null
    private val timeBroadcastReceiver = TimeBroadcastReceiver()
    private val callReceiver = IncomingCallReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_PHONE_NUMBERS
                ),
                permRequestCode
            )
        }

        binding.apply {
            btnSendMessage.setOnClickListener {
            }

            btnRegister.setOnClickListener {
                registerBroadcastReceiver()
            }

            btnUnregister.setOnClickListener {
                unregisterBroadcastReceiver()
            }
        }

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