package com.example.vitea.models.timetable

data class Day(
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
    val attendance: Int,
    val getAttendanceDetails: List<GetAttendanceDetails>
)