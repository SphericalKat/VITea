package com.example.vitea.api

import javax.inject.Inject

class WebClient @Inject constructor(private val webService: WebService) : BaseApiClient() {
    suspend fun getTimeTable(regNo: String) = getResult {
        webService.getTimeTable(regNo)
    }
}