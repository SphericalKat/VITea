package com.example.vitea.di.api

import com.example.vitea.BuildConfig
import com.example.vitea.api.WebService
import com.example.vitea.api.getOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val BASE_URL =
    if (BuildConfig.DEBUG) "https://vitian-wrapper-debug.herokuapp.com/" else "https://vitian-wrapper.herokuapp.com/"

@Module
@InstallIn(SingletonComponent::class)
object ApiComponent {
    @Provides
    @Singleton
    fun providesRetrofitService(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {
    @Provides
    fun providesWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientModule {
    @Provides
    fun providesOkHttpClient() = getOkHttpClient()
}