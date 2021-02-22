package com.example.vitea.api

import javax.inject.Inject

class WebClient @Inject constructor(private val webService: WebService) : BaseApiClient() {
    suspend fun getTimeTable(regNo: String) = getResult { webService.getTimeTable(regNo) }

    suspend fun getProfile(regNo: String) = getResult { webService.getProfile(regNo) }

    suspend fun getDa(regNo: String) = getResult { webService.getDa(regNo) }
}