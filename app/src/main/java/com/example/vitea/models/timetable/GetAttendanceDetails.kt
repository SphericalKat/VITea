package com.example.vitea.models.timetable

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class GetAttendanceDetails(
    val attendanceSlot: String,
    val attendanceDay: String,
    val attendanceDate: String,
    val attendanceStatus: String,
    val classTime: String
) : Parcelable