package com.example.appication_fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.appication_fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Button.setOnClickListener {
            performFragmentTransactions()
        }

        supportFragmentManager.setFragmentResultListener("key", this) { requestKey , bundle ->
            val result = bundle.get("message")
            if ( result != null){
                binding.Button.visibility = View.GONE
            }
        }
    }

    private fun performFragmentTransactions() {
        val currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        if ( currentFragment != null){
            fragmentTransaction.remove(currentFragment).commit()
        } else {
            fragmentTransaction.add(R.id.fragment_container, MyFragment.newInstance()).commit()
        }
    }
}