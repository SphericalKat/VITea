package com.example.vitea.ui.base

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.example.vitea.R

class BaseProgressDialog(activity: Activity) {
    private val dialog: Dialog = Dialog(activity, R.style.ProgressDialogTheme)

    fun showDialog() {
        dialog.show()
    }

    fun hideDialog() {
        dialog.dismiss()
    }

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading_progress)
    }
}