package com.example.broadcastexample.listeners

import android.content.Context
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class MyPhoneStateListener : PhoneStateListener() {

    private val logTag = "MyPhoneStateListener"
    private var savedNumber : String? = null

    private var context: Context? = null

    fun setContext(context: Context?) {
        this.context = context
    }

    fun setOutgoingNumber(number: String?) {
        this.savedNumber = number
    }

    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        super.onCallStateChanged(state, phoneNumber)

        Log.d(logTag,"Incoming call state: $state")

        when(state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                Log.d(logTag, "Incoming call..")
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                Log.d(logTag, "Phone call is started...")
            }
            TelephonyManager.CALL_STATE_IDLE -> {
                Log.d(logTag, "Phone call ended...")
            }
            else -> true
        }

        Log.d(logTag, "Incoming phone number: $phoneNumber")
    }
}