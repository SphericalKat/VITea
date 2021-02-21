package com.example.vitea.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.vitea.databinding.ActivityAuthBinding
import com.example.vitea.ui.base.BaseActivity
import com.example.vitea.ui.home.MainActivity
import com.example.vitea.utils.PreferenceHelper.set
import com.example.vitea.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    @Inject lateinit var prefs: SharedPreferences

    private val years = listOf("16", "17", "18", "19", "20")
    private val binding by viewBinding(ActivityAuthBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (prefs.contains("reg_no")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.loginButton.setOnClickListener {
            val regNo = binding.regNoTIL.editText?.text.toString()
            if (regNo.length < 9) {
                binding.regNoTIL.error = "Invalid reg no!"
                return@setOnClickListener
            }

            if (regNo.substring(0, 2) !in years) {
                binding.regNoTIL.error = "Invalid reg no!"
                return@setOnClickListener
            }

            val regInt = regNo.substring(5, 9).toIntOrNull()
            if (regInt == null || regInt < 0 || regInt > 9999) {
                binding.regNoTIL.error = "Invalid reg no!"
                return@setOnClickListener
            }

            prefs["reg_no"] = regNo
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

//        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}