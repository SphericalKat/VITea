package com.example.vitea.models.timetable

data class GetAttendanceDetails (
	val attendanceSlot : String,
	val attendanceDay : String,
	val attendanceDate : String,
	val attendanceStatus : String,
	val classTime : String
)