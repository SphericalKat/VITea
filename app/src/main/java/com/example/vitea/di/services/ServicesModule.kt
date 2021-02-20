package com.example.vitea.di.services

import android.app.AlarmManager
import android.content.Context
import android.content.Context.ALARM_SERVICE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    @Singleton
    fun alarmManager(@ApplicationContext context: Context) = context.getSystemService(ALARM_SERVICE) as AlarmManager
}