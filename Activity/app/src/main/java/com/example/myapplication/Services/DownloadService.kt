package com.example.myapplication.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast



class DownloadService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        Toast.makeText(this, " MyService Created ", Toast.LENGTH_LONG).show()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Toast.makeText(this, " MyService Started", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show()
    }
}