package com.example.broadcastexample.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import com.example.broadcastexample.listeners.MyPhoneStateListener
import java.lang.Exception

class IncomingCallReceiver : BroadcastReceiver() {

    private val logTag = "IncomingCallReceiver"
    private var phoneStateListener: MyPhoneStateListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.let {
            context?.let { ctx ->
                if(phoneStateListener == null) {
                    phoneStateListener = MyPhoneStateListener()
                }

                if (it.action?.equals("android.intent.action.NEW_OUTGOING_CALL") == true) {
                    var phoneNumber = it.extras?.getString("android.intent.extra.PHONE_NUMBER")
                    phoneStateListener?.setOutgoingNumber(phoneNumber)
                    return
                }

                phoneStateListener?.setContext(context)
                val telephonyManager = ctx.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
            }

        }
    }

}