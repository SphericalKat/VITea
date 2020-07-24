package com.example.vitea.api

class WebClient(private val webService: WebService) : BaseApiClient() {
    suspend fun getTimeTable(regNo: String) = getResult {
        webService.getTimeTable(regNo)
    }
}