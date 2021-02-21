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

private val BASE_URL = "https://vitian-v2.herokuapp.com/"

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

    @Provides
    fun providesWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Provides
    fun providesOkHttpClient() = getOkHttpClient()

}