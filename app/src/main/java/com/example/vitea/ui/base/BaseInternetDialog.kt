package com.example.vitea.ui.base

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.example.vitea.R

class BaseInternetDialog(activity: Activity) {
    private val dialog: Dialog = Dialog(activity, R.style.ProgressDialogTheme)

    fun showInternetLostDialog() {
        dialog.show()
    }

    fun hideInternetLostDialog() {
        dialog.dismiss()
    }

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_internet_lost)
    }
}