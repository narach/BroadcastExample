package com.example.broadcastexample.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context, "Обнаружено сообщение: " + intent.getStringExtra("com.example.broadcastexample.Message"),
            Toast.LENGTH_LONG).show()
    }
}