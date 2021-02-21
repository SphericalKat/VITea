package com.example.vitea.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.vitea.R
import com.example.vitea.databinding.ActivityMainBinding
import com.example.vitea.ui.auth.AuthActivity
import com.example.vitea.ui.base.BaseActivity
import com.example.vitea.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).findNavController()
    }

    @Inject lateinit var prefs: SharedPreferences

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!prefs.contains("reg_no")) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val spannable = SpannableString("VITea")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.neon_green)),
            2, 5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.toolbarText.text = spannable

        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}