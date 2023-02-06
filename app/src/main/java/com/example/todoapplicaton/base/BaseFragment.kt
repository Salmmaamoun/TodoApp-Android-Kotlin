package com.example.todoapplicaton.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    var progressDialog: ProgressDialog? = null
    fun showLodingDialog() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog?.setMessage("Loding...")
        progressDialog?.show()
    }

    fun hideLoding() {
        progressDialog?.dismiss()
    }

    var alertDialog: AlertDialog? = null
    fun showMessage(
        message: String,
        posActiontitle: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionTitle: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        canable: Boolean = true


    ) {
        var messageDialodBuilder = AlertDialog.Builder(requireContext())
        messageDialodBuilder.setMessage(message)
        if (posActiontitle != null) {
            messageDialodBuilder.setPositiveButton(posActiontitle,
                posAction ?: DialogInterface.OnClickListener { dialog, i->
                    dialog.dismiss()
                }

            )

        }
        if (negActionTitle != null) {
            messageDialodBuilder.setNegativeButton(negActionTitle,
                negAction ?: DialogInterface.OnClickListener { dialog, i ->
                    dialog.dismiss()
                })
        }
        messageDialodBuilder.setCancelable(canable)
        alertDialog=messageDialodBuilder.show()
    }
}