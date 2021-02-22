package com.example.vitea.repository

import com.example.vitea.api.WebClient
import javax.inject.Inject

class WebRepo @Inject constructor(private val webClient: WebClient) : BaseRepo() {
    fun getTimeTable(regNo: String) = makeRequest {
        webClient.getTimeTable(regNo)
    }

    suspend fun getTimeTableAsync(regNo: String) = webClient.getTimeTable(regNo)
    suspend fun getProfileAsync(regNo: String) = webClient.getProfile(regNo)
    suspend fun getDaAsync(regNo: String) = webClient.getDa(regNo)
}