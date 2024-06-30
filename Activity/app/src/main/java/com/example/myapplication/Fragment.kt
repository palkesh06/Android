package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels

class Fragment : Fragment() {

    private lateinit var listener: OnFragmentInteractionListener

    private val sharedViewModel: MainViewModel by activityViewModels()

    interface OnFragmentInteractionListener {
        fun onButtonClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (context is OnFragmentInteractionListener) {
            listener = context as OnFragmentInteractionListener
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_, container, false)
        val button : Button = view.findViewById(R.id.fragmentButton)
        button.setOnClickListener {
            //listener.onButtonClicked()
            sharedViewModel.setText("Button clicked in Fragment!")
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        //listener = null
    }
}