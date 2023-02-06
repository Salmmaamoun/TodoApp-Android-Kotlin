package com.example.todoapplicaton.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null
    fun showLodingDialog() {
        progressDialog = ProgressDialog(this)
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
        var messageDialodBuilder = AlertDialog.Builder(this)
        messageDialodBuilder.setMessage(message)
        if (posActiontitle != null) {
            messageDialodBuilder.setPositiveButton(posActiontitle,
                posAction ?: DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }

            )

        }
        if (negActionTitle != null) {
            messageDialodBuilder.setNegativeButton(negActionTitle,
                negAction ?: DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        }
        messageDialodBuilder.setCancelable(canable)
        alertDialog=messageDialodBuilder.show()
    }
}