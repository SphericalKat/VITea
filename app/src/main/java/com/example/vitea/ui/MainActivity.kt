package com.example.vitea.ui

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
import com.example.vitea.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val spannable = SpannableString("VITea")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.neon_green)),
            2, 5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        toolbar_text.text = spannable

        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNav, navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}