package com.example.appication_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.appication_fragment.databinding.FragmentChildBinding

class ChildFragment : BaseFragment() {

    private var _binding: FragmentChildBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentChildBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Button.setOnClickListener {
            fragmentManager?.apply {
                setFragmentResult(
                    "key",
                    bundleOf("message" to "This is a result from Child fragment")
                )
                beginTransaction().remove(this@ChildFragment).commit()
            }
            MyDialogFragment.newInstance().show(parentFragmentManager, MyDialogFragment.TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ChildFragment()
    }
}
