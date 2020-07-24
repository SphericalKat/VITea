package com.example.vitea.models.timetable

data class TimeTable(
    val THU: List<Lecture>,
    val TUE: List<Lecture>,
    val WED: List<Lecture>,
    val FRI: List<Lecture>,
    val MON: List<Lecture>
)