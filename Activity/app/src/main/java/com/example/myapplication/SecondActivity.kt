package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecondActivity : AppCompatActivity() {

    val COUNTER_KEY: String = "counter_key"
    private var counter : Int= 0
    private var editTextViewMessage : String? = null
    private lateinit var editTextView: TextView
    private lateinit var encryptedSharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        // Initialize EncryptedSharedPreferences
        initializeEncryptedSharedPreferences()


        val message = intent.getStringExtra("Message")
        val textView : TextView = findViewById(R.id.textView)
        textView.text = message


        val counterTextView : TextView = findViewById(R.id.counterTextView)
        if ( savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY, 0)
        }
        // Increment the counter
        counter++
        // Update the TextView with the new counter value
        counterTextView.text = "Counter: " + counter

        editTextView = findViewById(R.id.editTextView)
        editTextView.text = editTextViewMessage ?: getMessageFromSharedPreference().also { editTextViewMessage = it }


        handleImplicitIntent()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_KEY, counter)
        editTextViewMessage = editTextView.text.toString()
        saveEditTextViewToEncryptedSharedPreferences(editTextViewMessage.toString())
    }

    override fun onResume() {
        super.onResume()
        editTextView.text = getMessageFromSharedPreference()
    }
    override fun onPause() {
        super.onPause()
        editTextViewMessage = editTextView.text.toString()
        saveEditTextViewToEncryptedSharedPreferences(editTextViewMessage.toString())
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun initializeEncryptedSharedPreferences() {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        encryptedSharedPreferences = EncryptedSharedPreferences.create(
            "preference_file_name",
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun getMessageFromSharedPreference() : String {
        return encryptedSharedPreferences?.getString("message", "").toString()
    }
    private fun saveEditTextViewToEncryptedSharedPreferences(message: String) {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        encryptedSharedPreferences = EncryptedSharedPreferences.create(
            "preference_file_name",
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        encryptedSharedPreferences!!.edit().putString("message", message).apply()
    }


    private fun handleImplicitIntent() {
        if ( intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val sharedText : String? = intent.getStringExtra(Intent.EXTRA_TEXT)
            sharedText.let {
                val textView : TextView = findViewById(R.id.textView)
                textView.text = sharedText
            }
        }
    }
}