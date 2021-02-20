package com.example.vitea.ui.base

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.vitea.utils.isNetworkAvailable

open class BaseActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "erp_app"
    }

    private lateinit var progressDialog: BaseProgressDialog
    private lateinit var internetLostDialog: BaseInternetDialog

    private val isInternetAvailable = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = BaseProgressDialog(this)
        internetLostDialog = BaseInternetDialog(this)
        createNotificationChannel()
        setupNetworkListener()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "VITea updates",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }
    }

    fun hideSoftKeyBoard() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    fun showProgressDialog() {
        if (!isFinishing) {
            progressDialog.showDialog()
        }
    }

    fun hideProgressDialog() {
        if (isFinishing) {
            return
        }
        progressDialog.hideDialog()
    }

    fun showInternetLostDialog() {
        if (!isFinishing) {
            internetLostDialog.showInternetLostDialog()
        }
    }

    fun hideInternetLostDialog() {
        if (isFinishing) {
            return
        }
        internetLostDialog.hideInternetLostDialog()
    }

    fun stopInteraction() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun resumeInteraction() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    @SuppressLint("ServiceCast")
    private fun setupNetworkListener() {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback: ConnectivityManager.NetworkCallback =
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isInternetAvailable.postValue(true)
                }

                override fun onLost(network: Network) {
                    isInternetAvailable.postValue(false)
                }

                override fun onUnavailable() {
                    isInternetAvailable.postValue(false)
                }
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(networkCallback)
        }

        if (!isNetworkAvailable()) {
            showInternetLostDialog()
        }


        listenForInternetChanges()
    }

    private fun listenForInternetChanges() {
        isInternetAvailable.observe(this, {
            when (it) {
                false -> showInternetLostDialog()
                else -> hideInternetLostDialog()
            }
        })
    }
}