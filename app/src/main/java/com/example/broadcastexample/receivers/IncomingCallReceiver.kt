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

    private var incomingCall = false

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if(it.action.equals("android.intent.action.PHONE_STATE")) {
                val phoneState = it.getStringExtra(TelephonyManager.EXTRA_STATE)
                if(phoneState?.equals(TelephonyManager.EXTRA_STATE_RINGING) == true) {
                    //Трубка не поднята, телефон звонит
                    // The handset is not lifted, the phone rings
                    val phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                    incomingCall = true
                    Log.d("IncomingCallReceiver", "Incoming call from $phoneNumber")
                } else if(phoneState?.equals(TelephonyManager.EXTRA_STATE_OFFHOOK) == true) {
                    //Телефон находится в режиме звонка (набор номера при исходящем звонке / разговор)
                    Log.d("IncomingCallReceiver", "Call in progress..")
                    if(incomingCall) {
                        Log.d("IncomingCallReceiver","Close window.");
                        incomingCall = false;
                    }
                } else if(phoneState?.equals(TelephonyManager.EXTRA_STATE_IDLE) == true) {
                    //Телефон находится в ждущем режиме - это событие наступает по окончанию разговора или в ситуации "отказался поднимать трубку и сбросил звонок".
                    // The phone is in standby mode - this event occurs at the end of a conversation or situation, "refused to pick up the phone and dropped the call."
                    Log.d("IncomingCallReceiver", "Call is finished or dropped!")
                        if (incomingCall) {
                        Log.d("IncomingCallReceiver","Close window.");
                        incomingCall = false;
                    }
                }
            }
        }
    }

//    override fun onReceive(context: Context?, intent: Intent?) {
//        try {
//            var telManager =
//                context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//
//            var phoneListener = MyPhoneStateListener(context)
//
//            telManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE)
//        } catch (e: Exception) {
//            Log.e("Phone Receive Error", " " + e)
//        }
//    }
}