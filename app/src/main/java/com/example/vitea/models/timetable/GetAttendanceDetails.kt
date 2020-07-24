package com.example.vitea.models.timetable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetAttendanceDetails(
    val attendanceSlot: String,
    val attendanceDay: String,
    val attendanceDate: String,
    val attendanceStatus: String,
    val classTime: String
) : Parcelable