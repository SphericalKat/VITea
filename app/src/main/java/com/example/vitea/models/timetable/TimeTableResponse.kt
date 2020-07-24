package com.example.vitea.models.timetable

import androidx.annotation.Keep

@Keep
data class TimeTableResponse (
	val currentDay : String,
	val timeTable : TimeTable
)