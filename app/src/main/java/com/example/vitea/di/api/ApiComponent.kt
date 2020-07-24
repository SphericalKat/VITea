package com.example.vitea.di.api

import com.example.vitea.api.WebClient
import com.example.vitea.api.WebService
import com.example.vitea.api.getOkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://vitian-wrapper.herokuapp.com/"
val apiModule = module {
    factory { getOkHttpClient() }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(WebService::class.java) }
    factory { WebClient(get()) }
}