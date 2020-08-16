package com.example.vitea.di.api

import com.example.vitea.api.WebService
import com.example.vitea.api.getOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://vitian-wrapper.herokuapp.com/"

@Module
@InstallIn(ApplicationComponent::class)
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
@InstallIn(ApplicationComponent::class)
object WebServiceModule {
    @Provides
    fun providesWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)
}

@Module
@InstallIn(ApplicationComponent::class)
object OkHttpClientModule {
    @Provides
    fun providesOkHttpClient() = getOkHttpClient()
}