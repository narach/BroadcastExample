package com.example.broadcastexample.listeners

import android.content.Context
import android.telephony.PhoneStateListener
import android.util.Log
import android.widget.Toast

class MyPhoneStateListener(context: Context) : PhoneStateListener() {

    private val pContext = context

    override fun onCallStateChanged(state: Int, incomingNumber: String?) {
        Log.d("MyPhoneStateListener", "$state incoming no: $incomingNumber")

        // state = 1 means when phone is ringing
        if (state == 1) {
            val msg = " New Phone Call Event. Incomming Number : $incomingNumber"
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(pContext, msg, duration)
            toast.show()
        }
    }
}