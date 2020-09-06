package com.example.vitea.models.timetable

import androidx.annotation.Keep

@Keep
data class TimeTable(
    val THU: List<Lecture>,
    val TUE: List<Lecture>,
    val WED: List<Lecture>,
    val FRI: List<Lecture>,
    val MON: List<Lecture>,
    val SAT: List<Lecture>,
    val SUN: List<Lecture>
)