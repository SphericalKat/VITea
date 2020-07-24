package com.example.vitea.di.prefs

import com.example.vitea.BuildConfig
import com.example.vitea.utils.PreferenceHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { PreferenceHelper.customPrefs(androidContext(), "${BuildConfig.APPLICATION_ID}.pref") }
}