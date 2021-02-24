package com.example.vitea.models.timetable

import androidx.annotation.Keep

@Keep
data class Lecture(
    val venue: String,
    val img: String,
    val courseType: String,
    val studentUnits: Int,
    val patternId: Int,
    val slot: String,
    val totalUnits: Int,
    val inTime: String,
    val semesterId: String,
    val classId: String,
    val currentDay: String,
    val attendanceSlot: String,
    val name: String,
    val course: String,
    val outTime: String,
    val attendance: Float,
    val getAttendanceDetails: List<GetAttendanceDetails>
)