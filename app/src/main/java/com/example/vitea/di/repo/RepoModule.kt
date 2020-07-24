package com.example.vitea.di.repo

import com.example.vitea.repository.WebRepo
import org.koin.dsl.module

val repoModule = module {
    factory { WebRepo(get()) }
}