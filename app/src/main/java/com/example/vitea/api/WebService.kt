package com.example.vitea.api

import com.example.vitea.models.da.DAResponse
import com.example.vitea.models.profile.ProfileResponse
import com.example.vitea.models.timetable.TimeTableResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("timetable/{regNo}")
    suspend fun getTimeTable(@Path("regNo") regNo: String): Response<TimeTableResponse>

    @GET("profile/{regNo}")
    suspend fun getProfile(@Path("regNo") regNo: String): Response<ProfileResponse>

    @GET("da/{regNo}")
    suspend fun getDa(@Path("regNo") regNo: String): Response<DAResponse>
}