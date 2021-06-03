package com.example.broadcastexample.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.broadcastexample.MainActivity

class BootReceiver : BroadcastReceiver() {

    private val BOOT_ACTION = "android.intent.action.BOOT_COMPLETED"
    private var mContext: Context? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        mContext = context
        val action = intent?.action
        if(action.equals(BOOT_ACTION)) {

            // здесь ваш код
            // например, запускаем уведомление
//            Intent intent = new Intent(context, ru.alexanderklimov.NotifyService.NotifyService.class);
//            context.startService(intent);
            // в общем виде

            val activityIntent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context?.startActivity(activityIntent)

            //для Service
//            Intent serviceIntent = new Intent(context, MyService.class);
//            context.startService(serviceIntent);
        }
    }

}