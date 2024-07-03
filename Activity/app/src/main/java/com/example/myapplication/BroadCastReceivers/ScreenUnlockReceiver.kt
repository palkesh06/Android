package com.example.myapplication.BroadCastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ScreenUnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (Intent.ACTION_USER_PRESENT == intent.action) {
                // Screen is unlocked
                Toast.makeText(context, "Screen unlocked!", Toast.LENGTH_LONG).show();
            }
        }
    }
}