package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import android.Manifest
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), Fragment.OnFragmentInteractionListener{
    lateinit var  tvRef : TextView
    private lateinit var sharedViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonRef : Button = findViewById(R.id.button)
         tvRef = findViewById(R.id.tv)
        buttonRef.setOnClickListener {
            explicitIntent()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        val openAnotherActivity = findViewById<Button>(R.id.openAnotherActivityButton)
        openAnotherActivity.setOnClickListener{
            scheduledDelayedLaunch()
        }
        val cameraPermissions: Button = findViewById(R.id.cameraPermission)
        cameraPermissions.setOnClickListener {
            checkCameraPermission()
        }

        // Initialize SharedViewModel
        sharedViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Observe the LiveData
        sharedViewModel.text.observe(this@MainActivity) { it ->
            tvRef.text = it
        }

        if (savedInstanceState == null) {
            val fragment = Fragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ( requestCode == 100){
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission Granted!", Toast.LENGTH_SHORT).show();
                // Permission was granted, proceed with the camera functionality
                //useCamera();
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onButtonClicked() {
        tvRef.text = "Button clicked in Fragment!"
    }

    private fun checkCameraPermission() {
        if (checkSelfPermission( Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        }
        else {
            Toast.makeText(this, "Camera permission Already Granted!", Toast.LENGTH_SHORT).show();
            //useCamera()
        }
    }

    private  fun scheduledDelayedLaunch() {
        val intent = Intent ( this, SecondActivity::class.java).apply {
            putExtra("Message", "This is called from pending intent ")
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            SystemClock.currentThreadTimeMillis()+ 30* 1000,
            pendingIntent
        )
    }

    private fun implicitIntent() {
        val text = "This is an implicit Intent Passed from Another activity"
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val chooser : Intent = Intent.createChooser(intent, "Choose the app you want to use")
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Log.d("MainActivity", "Intent Failed "+e.message)
        }
    }

    private fun explicitIntent(){
        val intent : Intent = Intent(this, SecondActivity::class.java)
        val bundle : Bundle = Bundle()
        bundle.putString("Message", "This is Second Activity")
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private  fun showToast() {
        Toast.makeText(this, "Button is clicked", Toast.LENGTH_SHORT).show()
    }
}