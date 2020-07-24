package com.example.vitea.repository

import com.example.vitea.api.WebClient

class WebRepo(private val webClient: WebClient) : BaseRepo() {
    fun getTimeTable(regNo: String) = makeRequest {
        webClient.getTimeTable(regNo)
    }

    suspend fun getTimeTableAsync(regNo: String) = webClient.getTimeTable(regNo)
}