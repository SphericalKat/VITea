package com.example.vitea.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.vitea.R
import com.example.vitea.ui.base.BaseActivity
import com.example.vitea.ui.home.MainActivity
import com.example.vitea.utils.PreferenceHelper.set
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    @Inject lateinit var prefs: SharedPreferences

    private val years = listOf("16", "17", "18", "19", "20")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (prefs.contains("reg_no")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        loginButton.setOnClickListener {
            val regNo = regNoTIL.editText?.text.toString()
            if (regNo.length < 9) {
                regNoTIL.error = "Invalid reg no!"
                return@setOnClickListener
            }

            if (regNo.substring(0, 2) !in years) {
                regNoTIL.error = "Invalid reg no!"
                return@setOnClickListener
            }

            val regInt = regNo.substring(5, 9).toIntOrNull()
            if (regInt == null || regInt < 0 || regInt > 9999) {
                regNoTIL.error = "Invalid reg no!"
                return@setOnClickListener
            }

            prefs["reg_no"] = regNo
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}