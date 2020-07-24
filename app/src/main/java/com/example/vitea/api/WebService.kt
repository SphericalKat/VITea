package com.example.vitea.api

import com.example.vitea.models.timetable.TimeTableResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("timetable/{regNo}")
    suspend fun getTimeTable(@Path("regNo") regNo: String): Response<TimeTableResponse>
}