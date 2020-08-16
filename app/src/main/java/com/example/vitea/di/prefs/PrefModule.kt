package com.example.vitea.di.prefs

import android.content.Context
import com.example.vitea.BuildConfig
import com.example.vitea.utils.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PrefModule {
    @Provides
    @Singleton
    fun customPrefs(@ApplicationContext context: Context) =
        PreferenceHelper.customPrefs(context, "${BuildConfig.APPLICATION_ID}.pref")
}