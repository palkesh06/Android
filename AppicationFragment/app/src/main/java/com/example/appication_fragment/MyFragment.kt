package com.example.appication_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class MyFragment : Fragment() {

    private val TAG = "MyFragmentLifecycle"
    private lateinit var tv :TextView
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach() called")
        // Initialize resources needed for the fragment's entire lifecycle.
        // This includes accessing the parent activity context if needed.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        // Initialize components that you need to retain across configuration changes.
        // This is where you can set up your ViewModel and other retained data.
            childFragmentManager.setFragmentResultListener("key", this) { key , bundle ->
                tv.text = bundle.get("message").toString()
                fragmentManager?.setFragmentResult("key", bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChildFragment.newInstance())
                .commit()
        }
        val view = inflater.inflate(R.layout.fragment_my, container, false)
        tv = view.findViewById<TextView>(R.id.tv)
        return view
        // Initialize components that you need to retain across configuration changes.
        // This is where you can set up your ViewModel and other retained data.
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated() called")
        // Perform any final setup that requires the parent activity's onCreate() method to have completed.
        // This includes accessing the activity's views and setting up UI-related logic that depends on the activity's views.
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
        // Make the fragment visible to the user.
        // This is where you can register any UI-related observers or listeners that you need.
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
        // Make the fragment active and interactive.
        // This is where you start animations, open exclusive-access devices (e.g., the camera), or register any observers (e.g., LiveData observers).
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
        // Prepare the fragment for partial invisibility.
        // This is where you should save any changes that need to persist beyond the current user session.
        // Also, stop ongoing actions that should not continue while the fragment is in the background (e.g., pause animations).
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
        // Make the fragment invisible to the user.
        // This is where you can release resources that are not needed while the fragment is not visible (e.g., stop heavy processes, unregister UI-related observers).
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView() called")
        // Clean up resources related to the fragment's view hierarchy.
        // This includes clearing bindings or references to the fragment's view objects.
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
        // Clean up all other resources, including background threads, database connections, etc.
        // This is where you also clear any references to the activity to avoid memory leaks.
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach() called")
        // Final cleanup before the fragment is completely destroyed.
        // This is where you can perform operations that require context (e.g., removing context-based listeners).
    }

    companion object {
        fun newInstance() = MyFragment()
    }
}