package com.example.vitea.di

import com.example.vitea.di.api.apiModule
import com.example.vitea.di.repo.repoModule
import com.example.vitea.di.viewmodel.viewModelModule

val appComponent = listOf(
    apiModule,
    repoModule,
    viewModelModule
)