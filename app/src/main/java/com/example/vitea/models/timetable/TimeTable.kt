package com.example.vitea.models.timetable

data class TimeTable(
    val tHU: List<Day>,
    val tUE: List<Day>,
    val wED: List<Day>,
    val fRI: List<Day>,
    val mON: List<Day>
)