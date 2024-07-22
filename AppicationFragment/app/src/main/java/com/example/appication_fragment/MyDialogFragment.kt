package com.example.appication_fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf


class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("This is an alert dialog")
            .setPositiveButton("OK"
            ) { dialog, which ->
                fragmentManager?.setFragmentResult("key", bundleOf("message" to "From Dialog Fragment"))
                dialog.dismiss()
            }
            .setNegativeButton("Cancel"){ dialog , which ->
                dialog.dismiss()
            }
            .create()
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
    }

    companion object {
        const val TAG = "MyDialogFragment"
        fun newInstance() = MyDialogFragment()
    }

}